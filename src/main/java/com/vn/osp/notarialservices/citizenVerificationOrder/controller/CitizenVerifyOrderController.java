package com.vn.osp.notarialservices.citizenVerificationOrder.controller;

import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.service.CitizenVerifyOrderService;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.service.CustomerService;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.paymentTransaction.service.PaymentTransactionService;
import javafx.animation.PauseTransition;
import net.sf.jett.transform.ExcelTransformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping(value = "/citizen-verify-orders")
public class CitizenVerifyOrderController {


    @Autowired
    private  CitizenVerifyOrderService citizenVerifyOrderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CitizenVerifyOrderDTO> add(@RequestBody final CitizenVerifyOrderDTO citizenVerifyOrderDTO){
        CitizenVerifyOrderDTO result = citizenVerifyOrderService.insert(citizenVerifyOrderDTO).orElse(null);
        return ResponseEntity.ok(result);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CitizenVerifyOrderDTO> update(@RequestBody final CitizenVerifyOrderDTO citizenVerifyOrderDTO){
        CitizenVerifyOrderDTO result = citizenVerifyOrderService.update(citizenVerifyOrderDTO, true).orElse(null);
        return ResponseEntity.ok(result);
}

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<PagingResult> page(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "numberPerPage", required = true, defaultValue = "20") int numberPerPage,
            @RequestParam(name = "order_id", required = false) String order_id,
            @RequestParam(name = "notary_office_code", required = false) String notary_office_code,
            @RequestParam(name = "province_code", required = false) String province_code,
            @RequestParam(name = "transaction_status", required = false) String transaction_status,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "update_by", required = false) String update_by_officer,
            @RequestParam(name = "update_user_name", required = false) String update_user_name,
            @RequestParam(name = "order_time_from", required = false) String transaction_time_from,
            @RequestParam(name = "order_time_to", required = false) String transaction_time_to)
    {
        PagingResult pageResult = citizenVerifyOrderService.page(page, numberPerPage, order_id, notary_office_code, province_code,
                transaction_status, status,
                update_by_officer, transaction_time_from, transaction_time_to,
                update_user_name).orElse(new PagingResult());
        return ResponseEntity.ok(pageResult);
    }

    @RequestMapping(value = "/export-data-for-notary-office-side", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataForNotaryOfficeSide(
            @RequestParam(name = "order_id", required = false) String order_id,
            @RequestParam(name = "notary_office_code") String notary_office_code,
            @RequestParam(name = "province_code", required = false) String province_code,
            @RequestParam(name = "transaction_status", required = false) String transaction_status,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "update_by", required = false) String update_by,
            @RequestParam(name = "order_time_from", required = false) String order_time_from,
            @RequestParam(name = "order_time_to", required = false) String order_time_to)
    {
        Map<String, Object> result = new HashMap<>();
        Long count = citizenVerifyOrderService.count(order_id, notary_office_code, province_code, transaction_status, status,
                update_by, order_time_from, order_time_to, null).orElse(0L);
        List<CitizenVerifyOrderDTO> items = citizenVerifyOrderService.filter(order_id, notary_office_code, province_code, transaction_status, status,
                update_by, order_time_from, order_time_to, null).orElse(new ArrayList<>());
        Map sumData = citizenVerifyOrderService.getSumCitizenVerifyOrder(order_id, notary_office_code,
                province_code, transaction_status, status, update_by,order_time_from, order_time_to, null).orElse(null);
        result.put("count", count);
        result.put("items", items);
        result.put("totalData", sumData);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{order_id}", method = RequestMethod.GET)
    public ResponseEntity<CitizenVerifyOrderDTO> getDetail(@PathVariable("order_id") String order_id){
        CitizenVerifyOrderDTO result = citizenVerifyOrderService.getDetail(order_id).orElse(null);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(name = "order_id", required = false) String order_id,
                       @RequestParam(name = "notary_office_code", required = false) String notary_office_code,
                       @RequestParam(name = "province_code", required = false) String province_code,
                       @RequestParam(name = "transaction_status", required = false) String transaction_status,
                       @RequestParam(name = "status", required = false) String status,
                       @RequestParam(name = "update_by", required = false) String update_by,
                       @RequestParam(name = "order_time_from", required = false) String order_time_from,
                       @RequestParam(name = "order_time_to", required = false) String order_time_to,
                       @RequestParam(name = "update_user_name", required = false) String update_user_name,
                       HttpServletResponse response, HttpServletRequest request) {
        try {
            Long total = citizenVerifyOrderService.count(order_id, notary_office_code, province_code, transaction_status, status,
                    update_by,order_time_from, order_time_to, update_user_name).orElse(0L);
            List<CitizenVerifyOrderDTO> items = citizenVerifyOrderService.filter(order_id, notary_office_code,
                    province_code, transaction_status, status, update_by,order_time_from, order_time_to, update_user_name).orElse(new ArrayList<>());
            Map sumData = citizenVerifyOrderService.getSumCitizenVerifyOrder(order_id, notary_office_code,
                    province_code, transaction_status, status, update_by,order_time_from, order_time_to, update_user_name).orElse(null);
            String notary_office_name = "";
            if(StringUtils.isNotBlank(notary_office_code)) {
                List<CustomerDTO> customerDTOS = customerService.getCustomerByCode(" where code = '"+notary_office_code+"'").orElse(null);//todo get notary office name
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
            beans.put("order_time_from", order_time_from);
            beans.put("order_time_to", order_time_to);
            beans.put("totalData", sumData);

            beans.put("agency",notary_office_name);
            /*Resource ClassPathResource se lay dc thu muc resource trong project, ngay ca khi chay app*/
            Resource resource = new ClassPathResource("file/report/BaoCaoGiaoDichPhiXacThucDanhTinh_template.xls");

            InputStream fileIn = resource.getInputStream();

            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaoCaoGiaoDichPhiXacThucDanhTinh.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/retrieve-accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Map>> retrieveAccountFromData(@RequestParam(name = "province_code", required = false) String province_code,
                                                       @RequestParam(name = "notary_office_code", required = false) String notary_office_code){
        List<Map> items = citizenVerifyOrderService.retrieveUserAccountsFromData(province_code, notary_office_code).orElse(new ArrayList<>());
        return ResponseEntity.ok(items);
    }

}
