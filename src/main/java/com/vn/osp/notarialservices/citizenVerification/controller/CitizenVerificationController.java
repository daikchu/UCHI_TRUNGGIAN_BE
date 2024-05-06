package com.vn.osp.notarialservices.citizenVerification.controller;


import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.citizenVerification.service.CitizenVerficationService;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.service.CustomerService;
import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.paymentTransaction.service.OrderMapVerificationService;
import com.vn.osp.notarialservices.utils.AuthorizationUtil;
import net.sf.jett.transform.ExcelTransformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping(value = "/citizen-verifications")
public class CitizenVerificationController {
    @Autowired
    CitizenVerficationService citizenVerficationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderMapVerificationService orderMapVerificationService;


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<PagingResult> page(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "numberPerPage", required = true, defaultValue = "20") int numberPerPage,
            @RequestParam(name = "verify_id", required = false) String verify_id,
            @RequestParam(name = "order_id", required = false) String order_id,
            @RequestParam(name = "province_code", required = false) String province_code,
            @RequestParam(name = "notary_office_id", required = false) String notary_office_id,
            @RequestParam(name = "verify_status", required = false) String verify_status,
            @RequestParam(name = "verify_by", required = false) String verify_by,
            @RequestParam(name = "verify_by_name", required = false) String verify_by_name,
            @RequestParam(name = "citizen_verify_fullname", required = false) String citizen_verify_fullname,
            @RequestParam(name = "citizen_verify_cccd", required = false) String citizen_verify_cccd,
            @RequestParam(name = "verify_date_from", required = false) String verify_date_from,
            @RequestParam(name = "verify_date_to", required = false) String verify_date_to)
    {
        PagingResult pageResult = citizenVerficationService.page(page, numberPerPage, verify_id, province_code, notary_office_id,
                verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                verify_date_from, verify_date_to, order_id, verify_by_name).orElse(new PagingResult());
        return ResponseEntity.ok(pageResult);
    }

    @RequestMapping(value = "/{verify_id}", method = RequestMethod.GET)
    public ResponseEntity<CitizenVerificationsDTO> getDetail(@PathVariable("verify_id") String verify_id){
        CitizenVerificationsDTO result = citizenVerficationService.getDetail(verify_id).orElse(null);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(name = "verify_id", required = false) String verify_id,
                       @RequestParam(name = "order_id", required = false) String order_id,
                       @RequestParam(name = "province_code", required = false) String province_code,
                       @RequestParam(name = "notary_office_id", required = false) String notary_office_id,
                       @RequestParam(name = "verify_status", required = false) String verify_status,
                       @RequestParam(name = "verify_by", required = false) String verify_by,
                       @RequestParam(name = "citizen_verify_fullname", required = false) String citizen_verify_fullname,
                       @RequestParam(name = "citizen_verify_cccd", required = false) String citizen_verify_cccd,
                       @RequestParam(name = "verify_date_from", required = false) String verify_date_from,
                       @RequestParam(name = "verify_date_to", required = false) String verify_date_to,
                       @RequestParam(name = "verify_by_name", required = false) String verify_by_name,
                       HttpServletResponse response, HttpServletRequest request) {
        try {
            Long total = citizenVerficationService.count(verify_id, province_code, notary_office_id,
                    verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                    verify_date_from, verify_date_to, order_id, verify_by_name).orElse(0L);
            List<CitizenVerificationsDTO> items = citizenVerficationService.filter(verify_id, province_code, notary_office_id,
                    verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                    verify_date_from, verify_date_to, order_id, verify_by_name).orElse(new ArrayList<>());
            String notary_office_name = "";
            if(StringUtils.isNotBlank(notary_office_id)) {
                List<CustomerDTO> customerDTOS = customerService.getCustomerByCode(" where code = '"+notary_office_id+"'").orElse(null);//todo get notary office name
                if(customerDTOS != null && customerDTOS.size() > 0) {
                    notary_office_name = customerDTOS.get(0).getName();
                }
            }

            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("report", items);
            beans.put("total",total);
            Date date = new Date(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            beans.put("year", year);
            beans.put("month", month);
            beans.put("day", day);
            beans.put("verify_date_from", verify_date_from);
            beans.put("verify_date_to", verify_date_to);
            beans.put("agency", notary_office_name);
            /*boolean org_type=false;
            if(SystemProperties.getProperty("org_type").equals("1")){
                org_type=true;
            }
            beans.put("org_type", org_type);*/

//            String office_name=accessHistoryService.getConfigValue("notary_office_name").orElse(null);
            /*Resource ClassPathResource se lay dc thu muc resource trong project, ngay ca khi chay app*/
            Resource resource = new ClassPathResource("file/report/BaoCaoSoLuotXacThucDanhTinh_template.xls");

            InputStream fileIn = resource.getInputStream();

            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaoCaoSoLuotXacThucDanhTinh.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/export-data-for-notary-office-side", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataForNotaryOfficeSide(
            @RequestParam(name = "verify_id", required = false) String verify_id,
            @RequestParam(name = "order_id", required = false) String order_id,
            @RequestParam(name = "province_code", required = false) String province_code,
            @RequestParam(name = "notary_office_id", required = false) String notary_office_id,
            @RequestParam(name = "verify_status", required = false) String verify_status,
            @RequestParam(name = "verify_by", required = false) String verify_by,
            @RequestParam(name = "citizen_verify_fullname", required = false) String citizen_verify_fullname,
            @RequestParam(name = "citizen_verify_cccd", required = false) String citizen_verify_cccd,
            @RequestParam(name = "verify_date_from", required = false) String verify_date_from,
            @RequestParam(name = "verify_date_to", required = false) String verify_date_to,
            @RequestParam(name = "verify_by_name", required = false) String verify_by_name
    )
    {
        Map<String, Object> result = new HashMap<>();
        Long count = citizenVerficationService.count(verify_id, province_code, notary_office_id, verify_status, verify_by,
                citizen_verify_fullname, citizen_verify_cccd, verify_date_from, verify_date_to, order_id, verify_by_name).orElse(0L);
        List<CitizenVerificationsDTO> items = citizenVerficationService.filter(verify_id, province_code, notary_office_id, verify_status, verify_by,
                citizen_verify_fullname, citizen_verify_cccd, verify_date_from, verify_date_to, order_id, verify_by_name).orElse(new ArrayList<>());
        result.put("count", count);
        result.put("items", items);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/retrieve-accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Map>> retrieveAccountFromData(@RequestParam(name = "province_code", required = false) String province_code,
                                                             @RequestParam(name = "notary_office_code", required = false) String notary_office_code){
        List<Map> items = citizenVerficationService.retrieveUserAccountsFromData(province_code, notary_office_code).orElse(new ArrayList<>());
        return ResponseEntity.ok(items);
    }

    @RequestMapping(value = "/update-status-verification", method = RequestMethod.POST)
    public ResponseEntity<Object> updateStatusVerification(@RequestHeader(value = "Authorization") String authorization, @RequestBody OrderMapVerification orderMapVerification, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            return new ResponseEntity<>(orderMapVerificationService.saveOrUpdate(orderMapVerification).orElse(null), HttpStatus.OK);
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
}
