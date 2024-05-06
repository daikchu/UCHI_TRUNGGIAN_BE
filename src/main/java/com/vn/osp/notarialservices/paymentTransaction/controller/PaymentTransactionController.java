package com.vn.osp.notarialservices.paymentTransaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vn.osp.notarialservices.bankPayment.domain.BankPaymentBO;
import com.vn.osp.notarialservices.bankPayment.repository.BankPaymentRepository;
import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.service.CitizenVerficationNumberService;
import com.vn.osp.notarialservices.citizenVerificationOrder.domain.CitizenVerifyOrderBO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.service.CitizenVerifyOrderService;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;
import com.vn.osp.notarialservices.identityAuthentication.dto.*;
import com.vn.osp.notarialservices.identityAuthentication.service.PaymentService;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.paymentTransaction.service.OrderMapVerificationService;
import com.vn.osp.notarialservices.paymentTransaction.service.PaymentTransactionService;
import com.vn.osp.notarialservices.paymentTransaction.service.TokenService;
import com.vn.osp.notarialservices.utils.*;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value="/payment")
public class PaymentTransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentTransactionController.class);

    public static Logger getLOGGER() {
        return LOGGER;
    }
    private final PaymentService paymentService;
    private final BankPaymentRepository bankPaymentRepository;
    private final PaymentTransactionService paymentTransactionService;
    private final CitizenVerifyOrderService citizenVerifyOrderService;
    private final CitizenVerficationNumberService citizenVerficationNumberService;
    private final TokenService tokenService;
    private final OrderMapVerificationService orderMapVerificationService;
    @Autowired
    public PaymentTransactionController(PaymentService paymentService, BankPaymentRepository bankPaymentRepository,PaymentTransactionService paymentTransactionService, CitizenVerifyOrderService citizenVerifyOrderService,CitizenVerficationNumberService citizenVerficationNumberService, TokenService tokenService,OrderMapVerificationService orderMapVerificationService){
        this.paymentService = paymentService;
        this.bankPaymentRepository = bankPaymentRepository;
        this.paymentTransactionService = paymentTransactionService;
        this.citizenVerifyOrderService = citizenVerifyOrderService;
        this.citizenVerficationNumberService = citizenVerficationNumberService;
        this.tokenService = tokenService;
        this.orderMapVerificationService = orderMapVerificationService;
    }

    @RequestMapping(value = "/token_generate", method = RequestMethod.POST)
    public ResponseEntity<Object> getTokenGenerate(@RequestHeader(value = "Authorization") String authorization, HttpServletRequest request){
        ResultTokenGenerate result = new ResultTokenGenerate();
        // Extract username and password
        String[] credentials = Crypter.parseBasicAuthHeader(authorization);
        if (credentials != null && credentials.length == 2) {
            String username = credentials[0];
            String password = credentials[1];
            // giai ma user/pass
            username = Crypter.decrypt(username);
            password = Crypter.decrypt(password);
            // case giải mã user/pass fail
            if(username == null || password == null){
                result.setTimestamp(new Date().getTime());
                result.setStatus(401);
                result.setError("Unauthorized");
                result.setMessage("");
                result.setPath(request.getRequestURI());
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
            if(username.equals(Constants.HEADER_AUTH_BASIC.USERNAME) && password.equals(Constants.HEADER_AUTH_BASIC.PASSWORD)){
                ObjectTokenJsonOSP objectTokenJson = TokenGenerateUtil.createAccessToken(username,password);
                return new ResponseEntity<>(objectTokenJson, HttpStatus.OK);
            }else {
                result.setTimestamp(new Date().getTime());
                result.setStatus(401);
                result.setError("Unauthorized");
                result.setMessage("");
                result.setPath(request.getRequestURI());
                return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            }
        }else {
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/getTokenVietQR", method = RequestMethod.POST)
    public ResponseEntity<Object> getTokenVietQR(@RequestHeader(value = "Authorization") String authorization, HttpServletRequest request){
        return tokenService.getTokenVietQR(authorization, request);
    }

    @RequestMapping(value= "/generateVietQRCode", method = RequestMethod.POST)
    public ResponseEntity<Object> generateVietQRCode(@RequestHeader(value = "Authorization") String authorization, @RequestBody FormInputGenerateVietQR formInputGenerateVietQR, HttpServletRequest request){

        // get token VietQR
        ResponseEntity<Object> objectTokenJson = tokenService.getTokenVietQR(authorization, request);
        if(objectTokenJson.getStatusCode() == HttpStatus.OK) {
            ResultCode result = new ResultCode();
            List<BankBeneficiaryBO> bankBeneficiaryBOList = new ArrayList<>();
            List<BankPaymentBO> bankPaymentBOList = new ArrayList<>();
            JSONObject jsonObject = null;
            try {
                ObjectTokenJson output = mapperObject(objectTokenJson.getBody());
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", output.getToken_type() + " " + output.getAccess_token());
                // lay danh sach tai khoan nguoi huong thu
                bankBeneficiaryBOList = paymentService.getListBankBeneficiary(formInputGenerateVietQR.getGenQRTransType()).orElse(new ArrayList<>());
                // lay danh sach ngan hang thanh toan
//            bankPaymentBOList = bankPaymentRepository.getListBankPayment().orElse(new ArrayList<>());
                if (!bankBeneficiaryBOList.isEmpty() && formInputGenerateVietQR.getGenQRTransType() == 1) { // tạo mã VietQR giao dịch cho TK ngân hàng liên kết
                    InputGenerateVietQRCodeWithBank inputGenerateVietQRCode = new InputGenerateVietQRCodeWithBank(
                            bankBeneficiaryBOList.get(0).getBankAccount(),
                            formInputGenerateVietQR.getAmount(),
                            bankBeneficiaryBOList.get(0).getContent(),
                            bankBeneficiaryBOList.get(0).getBankCode(),
                            bankBeneficiaryBOList.get(0).getTransType(),
                            bankBeneficiaryBOList.get(0).getUserBankName(),
                            formInputGenerateVietQR.getOrderId(),
                            bankBeneficiaryBOList.get(0).getSign());
                    jsonObject = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(inputGenerateVietQRCode));
                } else if (!bankBeneficiaryBOList.isEmpty() && formInputGenerateVietQR.getGenQRTransType() == 2) { // tạo mã VietQR giao dịch cho end-user
                    InputGenerateVietQRCodeWithEndUser inputGenerateVietQRCode = new InputGenerateVietQRCodeWithEndUser(
                            bankBeneficiaryBOList.get(0).getBankAccount(),
                            formInputGenerateVietQR.getAmount(),
                            bankBeneficiaryBOList.get(0).getContent(),
                            bankBeneficiaryBOList.get(0).getBankCode(),
                            bankBeneficiaryBOList.get(0).getTransType(),
                            bankBeneficiaryBOList.get(0).getUserBankName(),
                            formInputGenerateVietQR.getOrderId(),
                            bankBeneficiaryBOList.get(0).getSign(),
                            bankBeneficiaryBOList.get(0).getCustomerBankAccount(),
                            bankBeneficiaryBOList.get(0).getCustomerBankCode(),
                            bankBeneficiaryBOList.get(0).getCustomerName());
                    jsonObject = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(inputGenerateVietQRCode));
                }
                result = VietQrAPIUtil.callAPIGenerateVietQRCode(Constants.URL_CREATE_VIETQR_CODE, headers, jsonObject.toString());
                if (result.getStatus().equals("200")) {
                    result.getData().setOrderId(formInputGenerateVietQR.getOrderId());
                    return new ResponseEntity<>(result, HttpStatus.OK);
                } else if (result.getStatus().equals("FAILED")) {
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
                }
            } catch (Exception ex) {
                LOGGER.error("Có lỗi xảy ra: " + ex.getMessage());
                ex.printStackTrace();
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        }else {
            return objectTokenJson;
        }
    }


    @RequestMapping(value= "/transaction-sync", method = RequestMethod.POST)
    public ResponseEntity<Object> transactionSync(@RequestHeader(value = "Authorization") String authorization,@RequestHeader(value = "Content-Type") String contentType,@RequestBody FormInputTransactionSync formInputTransactionSync, HttpServletRequest request) {
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            OutputTransactionSync outputTransactionSync = new OutputTransactionSync();
            PaymentTransactions paymentTransactions = new PaymentTransactions();
            RefTransaction object = new RefTransaction();
            // validate thông tin trước khi lưu thông tin giao dịch nhận BĐSD
            ErrorCode errorCode = paymentService.validateInputTransactionSync(formInputTransactionSync).orElse(null);
            if (errorCode.getErrorCode().equals(Constants.ERROR_CODE_PAYMENT.SUCCESS.getErrorCode()) && errorCode.getMessage().equals(Constants.ERROR_CODE_PAYMENT.SUCCESS.getMessage())) {
                // case có mã đơn hàng khi nhận BĐSD
                if(formInputTransactionSync.getOrderId() != null && !formInputTransactionSync.getOrderId().isEmpty()){
                    // kiem tra cac thong tin giao dich nhan BDSD
                    String stringFilter = "where pt.order_id = '" + formInputTransactionSync.getOrderId() + "' and pt.transaction_status = '" + Constants.Status_PaymentTransaction.PENDING.getValue() + "'";
                    // lay thong tin cua ma hoa don duoc tao ra khi gen QR code
                    PaymentTransactions pay_transaction_status_pending = paymentTransactionService.getPaymentTransaction(stringFilter).orElse(new PaymentTransactions());
                    if (pay_transaction_status_pending.getId() != null && pay_transaction_status_pending.getTransaction_status().equals(Constants.Status_PaymentTransaction.PENDING.getValue())) {
                            // validate các trường thông tin giao dich nhan BDSD
                            ErrorCode errCode = paymentService.validateExitsTransaction(formInputTransactionSync, pay_transaction_status_pending).orElse(null);
                            if ((!errCode.getErrorCode().equals(Constants.ERROR_CODE_PAYMENT.SUCCESS.getErrorCode())
                                    && !errCode.getMessage().equals(Constants.ERROR_CODE_PAYMENT.SUCCESS.getMessage()))
                                    || (errCode == null || errCode == new ErrorCode())
                            ) {
                                outputTransactionSync.setError(true);
                                outputTransactionSync.setErrorReason(errCode.getErrorCode() != null ? errCode.getErrorCode() : Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
                                outputTransactionSync.setToastMessage(errCode.getMessage() != null ? errCode.getMessage() : "Có lỗi xảy ra tại OSP");
                                object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
                                outputTransactionSync.setObject(object);
                                return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
                            }
                    }
                    paymentTransactions = setPaymentTransactions(formInputTransactionSync, errorCode);
                    // xu ly case thanh toan thieu, thanh toan thua, sai noi dung
                    paymentTransactions = paymentService.handleTransactionSync(formInputTransactionSync, pay_transaction_status_pending, paymentTransactions).orElse(new PaymentTransactions());
                    // gennerate id tham chieu quan ly tai OSP
                    String refTransactionId = paymentService.genRefTransactionId().orElse("");
                    if (!refTransactionId.isEmpty()) {
                        paymentTransactions.setRef_transaction_id(refTransactionId);
                        // lưu thông tin giao dịch nhận BĐSD
                        PaymentTransactions result = paymentTransactionService.saveOrUpdateTransactionBO(paymentTransactions).orElse(new PaymentTransactions());

                        //cập nhật dữ liệu order theo thông tin giao dịch mới nhất
                        citizenVerifyOrderService.updateBySystem(paymentTransactions.getOrder_id(), paymentTransactions.getTransaction_status(),
                                paymentTransactions.getStatus(), paymentTransactions.getAmount(), paymentTransactions.getContent(), paymentTransactions.getNote());

                        if (result.getId() != null) {
                            if(result.getTransaction_status().equals(Constants.Status_PaymentTransaction.SUCCESS.getValue())){
                                // cập nhật số lượt xác thực cho VPCC
                                CitizenVerifyNumberBO citizenVerifyNumberBO = new CitizenVerifyNumberBO();
                                    List<CitizenVerifyOrderBO> citizenVerifyOrderBOS = citizenVerifyOrderService.getCitizenVerifyOrderByFilter(" where cvo.order_id = '" + paymentTransactions.getOrder_id() + "'").orElse(new ArrayList<>());
                                if (citizenVerifyOrderBOS != null && citizenVerifyOrderBOS.size() > 0) {
                                    citizenVerifyNumberBO.setNotary_office_code(citizenVerifyOrderBOS.get(0).getNotary_office_code());
                                    citizenVerifyNumberBO.setProvince_code(citizenVerifyOrderBOS.get(0).getProvince_code());
                                    citizenVerifyNumberBO.setVerify_number_total(citizenVerifyOrderBOS.get(0).getVerify_number());
                                    citizenVerifyNumberBO.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                                    citizenVerifyNumberBO.setEntry_date_time(TimeUtil.getTimeNow());
                                    citizenVerifyNumberBO.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
                                    citizenVerifyNumberBO.setUpdate_date_time(TimeUtil.getTimeNow());
                                    citizenVerficationNumberService.saveOrUpdate(citizenVerifyNumberBO, true);
                                    // sinh mã xác thực theo số lượng lượt xác thực của đơn hàng
                                    orderMapVerificationService.orderIdMapVerification(result.getOrder_id(), citizenVerifyOrderBOS.get(0).getVerify_number());
                                }
                            }
                            outputTransactionSync.setError(false);
                            outputTransactionSync.setErrorReason(errorCode.getErrorCode());
                            outputTransactionSync.setToastMessage("");
                            object.setReftransactionid(refTransactionId);
                            outputTransactionSync.setObject(object);
                        } else {
                            outputTransactionSync.setError(true);
                            outputTransactionSync.setErrorReason(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
                            outputTransactionSync.setToastMessage("Có lỗi xảy ra khi lưu giao dịch biến động số dư tại OSP");
                            object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
                            outputTransactionSync.setObject(object);
                            return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        outputTransactionSync.setError(true);
                        outputTransactionSync.setErrorReason(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
                        outputTransactionSync.setToastMessage("Lỗi không tạo ra ID tham chiếu quản lý tại OSP");
                        object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
                        outputTransactionSync.setObject(object);
                        return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
                    }
                }else {
                    paymentTransactions = setPaymentTransactions(formInputTransactionSync, errorCode);
                    // gennerate id tham chieu quan ly tai OSP
                    String refTransactionId = paymentService.genRefTransactionId().orElse("");
                    if (!refTransactionId.isEmpty()) {
                        paymentTransactions.setRef_transaction_id(refTransactionId);
                        paymentTransactions.setTransaction_status(Constants.Status_PaymentTransaction.SUCCESS.getValue());
                        // lưu thông tin giao dịch nhận BĐSD
                        PaymentTransactions result = paymentTransactionService.saveOrUpdateTransactionBO(paymentTransactions).orElse(new PaymentTransactions());
                        if(result.getId() != null){
                            outputTransactionSync.setError(false);
                            outputTransactionSync.setErrorReason(errorCode.getErrorCode());
                            outputTransactionSync.setToastMessage("");
                            object.setReftransactionid(refTransactionId);
                            outputTransactionSync.setObject(object);
                        }else {
                            outputTransactionSync.setError(true);
                            outputTransactionSync.setErrorReason(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
                            outputTransactionSync.setToastMessage("Có lỗi xảy ra khi lưu giao dịch biến động số dư tại OSP");
                            object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
                            outputTransactionSync.setObject(object);
                            return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
                        }
                    }
                }
//                else {
//                    outputTransactionSync.setError(true);
//                    outputTransactionSync.setErrorReason(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
//                    outputTransactionSync.setToastMessage("Lỗi mã hóa đơn của khách hàng không tồn tại");
//                    object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
//                    outputTransactionSync.setObject(object);
//                    return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
//                }
            } else {
                outputTransactionSync.setError(true);
                outputTransactionSync.setErrorReason(errorCode.getErrorCode());
                outputTransactionSync.setToastMessage(errorCode.getMessage());
                object.setReftransactionid(""); // lỗi không tạo ra ID tham chiếu quản lý bên đối tác
                outputTransactionSync.setObject(object);
                return new ResponseEntity<>(outputTransactionSync, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(outputTransactionSync, HttpStatus.OK);
        } else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<PaymentTransactionDTO> update(@RequestBody final PaymentTransactionDTO paymentTransactionDTO){
        PaymentTransactionDTO result = paymentTransactionService.update(paymentTransactionDTO).orElse(null);
        /*if(result != null) {
            CitizenVerifyOrderDTO citizenVerifyOrderDTO = citizenVerifyOrderService.getDetail(result.getOrder_id()).orElse(null);
            citizenVerifyOrderDTO.setUpdate_user_name(result.getUpdate_user_name());
            citizenVerifyOrderService.update(citizenVerifyOrderDTO);
        }*/
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/get-detail/{transaction_id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentTransactionDTO> getDetail(@PathVariable String transaction_id){
        PaymentTransactionDTO result = paymentTransactionService.getDetail(transaction_id).orElse(null);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/get-detail-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentTransactionDTO> getDetail(@PathVariable Long id){
        PaymentTransactionDTO result = paymentTransactionService.getById(id).orElse(null);
        return ResponseEntity.ok(result);
    }

   /* @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<PagingResult> getPage(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "numberPerPage", required = true, defaultValue = "20") int numberPerPage,
            @RequestParam(name = "order_id", required = false) String order_id,
            @RequestParam(name = "notary_office_code", required = false) String notary_office_code,
            @RequestParam(name = "province_code", required = false) String province_code,
            @RequestParam(name = "transaction_status", required = false) String transaction_status,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "update_by", required = false) String update_by,
            @RequestParam(name = "update_user_name", required = false) String update_user_name,
            @RequestParam(name = "order_time_from", required = false) String order_time_from,
            @RequestParam(name = "order_time_to", required = false) String order_time_to){
        PagingResult pageResult = paymentTransactionService.page(page, numberPerPage, order_id, notary_office_code, province_code,
                transaction_status, status,
                update_by, order_time_from, order_time_to,
                update_user_name).orElse(new PagingResult());
        return ResponseEntity.ok(pageResult);
        PaymentTransactionDTO result = paymentTransactionService.getById(id).orElse(null);
        return ResponseEntity.ok(result);
    }*/


    @RequestMapping(value = "/addPaymentTransaction", method = RequestMethod.POST)
    public ResponseEntity<Object> addPaymentTransaction(@RequestHeader(value = "Authorization") String authorization, @RequestBody final PaymentTransactionDTO paymentTransactionDTO, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            PaymentTransactionDTO result = paymentTransactionService.insert(paymentTransactionDTO, true).orElse(null);
            //todo update order
//            citizenVerifyOrderService.updateBySystem(result.getOrder_id(), result.getTransaction_status(), null, null, null, null);
            if (result.getId() != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        }else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/transactions/check-order", method = RequestMethod.POST)
    public ResponseEntity<Object> checkOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody final FormInputCheckOrderOfTransaction input, HttpServletRequest request){
        // get token VietQR
        ResponseEntity<Object> objectTokenJson = tokenService.getTokenVietQR(authorization, request);
        if(objectTokenJson.getStatusCode() == HttpStatus.OK){
            ResultOutputCheckOrder result = new ResultOutputCheckOrder();
            try {
                ObjectTokenJson output = mapperObject(objectTokenJson.getBody());
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", output.getToken_type() + " " + output.getAccess_token());

                FormInputCheckOrder inputCheckOrder = new FormInputCheckOrder();
                inputCheckOrder.setBankAccount(input.getBankAccount());
                // Phân loại check:
                // - 0: check bằng orderId
                // - 1: check bằng referenceNumber
                inputCheckOrder.setType(0);
                inputCheckOrder.setValue(input.getOrderId());
                String md5Hash = (Crypter.encodeToMD5(input.getBankAccount() + Constants.HEADER_AUTH_BASIC_VIETQR.USERNAME));
                inputCheckOrder.setCheckSum(md5Hash);
                JSONObject jsonObject = null;
                jsonObject = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(inputCheckOrder));
                result = VietQrAPIUtil.callAPICheckOrder(Constants.URL_CHECK_ORDER, headers, jsonObject.toString());
                if(result.getStatus().equals("200")){
                    return new ResponseEntity<>(result, HttpStatus.OK);
                }else if(result.getStatus().equals("FAILED")){
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
                }else {
                    return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
                }
            }catch(Exception ex) {
                LOGGER.error("Có lỗi xảy ra: " + ex.getMessage());
                ex.printStackTrace();
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        }else {
            return objectTokenJson;
        }
    }
    @RequestMapping(value = "/transactions/check-order-status", method = RequestMethod.POST)
    public ResponseEntity<Object> checkOrderStatus(@RequestHeader(value = "Authorization") String authorization, @RequestBody final String order_id, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            StringBuilder stringFilter = new StringBuilder();
            stringFilter.append("where pt.order_id = '" + order_id + "'");
            // lấy đơn hàng có trạng thái là thành công và thất bại
            stringFilter.append(" and pt.transaction_status in('success', 'fail')");
            stringFilter.append(" ORDER BY pt.update_date_time DESC, pt.entry_date_time DESC");
//            String stringFilter = "where pt.order_id = '" + order_id + "' and pt.transaction_status in( '" + Constants.Status_PaymentTransaction.SUCCESS.getValue() + "', '" + Constants.Status_PaymentTransaction.FAIL.getValue() + "')";
            PaymentTransactions result = paymentTransactionService.getPaymentTransaction(stringFilter.toString()).orElse(new PaymentTransactions());
            if(result.getId() != null){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");;
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateStatusOrderIdAfter15Minutes(){
       List<PaymentTransactions> result = paymentTransactionService.updateStatusOrderIdAfter15Minutes().orElse(new ArrayList<>());
        System.out.println(result);
    }

    private ObjectTokenJson mapperObject(Object body){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();
            String stringObject = gson.toJson(body);
            return mapper.readValue(stringObject, ObjectTokenJson.class);
        }catch (Exception ex){
            return null;
        }
    }
    private PaymentTransactions setPaymentTransactions(FormInputTransactionSync input, ErrorCode errorCode){
        PaymentTransactions result = new PaymentTransactions();
        result.setTransaction_id(input.getTransactionid());
        result.setTransaction_time(String.valueOf(input.getTransactiontime()));
        result.setReference_number(input.getReferencenumber());
        result.setAmount(String.valueOf(input.getAmount()));
        result.setContent(input.getContent());
        result.setBank_account(input.getBankaccount());
        result.setTrans_type(input.getTransType());
        result.setOrder_id(input.getOrderId());
        result.setEntry_date_time(TimeUtil.getTimeNow());
        result.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        result.setUpdate_date_time(TimeUtil.getTimeNow());
        result.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        result.setError(0);
        result.setError_reason(errorCode.getErrorCode());
        result.setToast_message("");
        return result;
    }
}
