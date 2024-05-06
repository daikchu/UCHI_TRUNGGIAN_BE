package com.vn.osp.notarialservices.contractfee.controller;

import com.vn.osp.notarialservices.contractfee.domain.ContractFeeBO;
import com.vn.osp.notarialservices.contractfee.dto.ContractFeeCodeTemplate;
import com.vn.osp.notarialservices.contractfee.service.ContractFeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018-05-09.
 */
@Controller
@RequestMapping(value="/contractfee")
public class ContractFeeController {
    private static final Logger LOG = LoggerFactory.getLogger(ContractFeeController.class);
    private final ContractFeeService contractFeeService;

    @Autowired
    public ContractFeeController(ContractFeeService contractFeeService){
        this.contractFeeService = contractFeeService;
    }

    @RequestMapping(value = "/AddContractFee",method = RequestMethod.POST)
    public ResponseEntity<Integer> addContractFee(@RequestBody final ContractFeeBO contractFeeBO){
        Integer id = contractFeeService.addContractFee(contractFeeBO).orElse(Integer.valueOf(0));
        return new ResponseEntity<Integer>(id,HttpStatus.OK);

    }
    @RequestMapping(value = "/UpdateContractFee",method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateContractFee(@RequestBody final ContractFeeBO contractFeeBO){
        Integer id = contractFeeService.updateContractFee(contractFeeBO).orElse(0);
        return new ResponseEntity<Integer>(id,HttpStatus.OK);
    }
    // lay list va phan trang
    @RequestMapping(value = "/getContractFeeCode",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeBO>> getContractFee(@RequestParam final int page,@RequestParam final String codeContract){

        List<ContractFeeBO> list = new ArrayList<>();
        list = contractFeeService.getContractFeeCode(page,codeContract).orElse(new ArrayList<ContractFeeBO>());
        return new ResponseEntity<List<ContractFeeBO>>(list,HttpStatus.OK);

    }
    // for add lấy list theo code de validate
    @RequestMapping(value = "/getContractFeeOnlyCode",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeBO>> getContractFeeOnlyCode(@RequestParam final Long codeContract){

        List<ContractFeeBO> list = new ArrayList<>();
        list = contractFeeService.getContractFeeOnlyCode(codeContract).orElse(new ArrayList<ContractFeeBO>());
        return new ResponseEntity<List<ContractFeeBO>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/getContractFeeAll",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeBO>> getContractFeeAll(){
        List<ContractFeeBO> list = new ArrayList<>();
        list = contractFeeService.getContractFeeAll().orElse(new ArrayList<ContractFeeBO>());
        return new ResponseEntity<List<ContractFeeBO>>(list,HttpStatus.OK);

    }
    @RequestMapping(value = "/countContractFeeAll",method = RequestMethod.GET)
    public ResponseEntity<Long> countContractFeeAll(@RequestParam final String codeContract){
        return new ResponseEntity<Long>(contractFeeService.countContractFeeAll(codeContract).orElse(Long.valueOf(0)),HttpStatus.OK);
    }

    @RequestMapping(value = "/getContractFeeId",method = RequestMethod.GET)
    public ResponseEntity<ContractFeeBO> getContractFeeId(@RequestParam final int id){
        ContractFeeBO list = new ContractFeeBO();
        list = contractFeeService.getContractFeeId(id).orElse(new ContractFeeBO());
        return new ResponseEntity<ContractFeeBO>(list,HttpStatus.OK);

    }
    @RequestMapping(value = "/deleteContractFee",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteContractFee(@RequestParam final int id){

        Boolean check = contractFeeService.deleteContractFee(id);
        return new ResponseEntity<Boolean>(check,HttpStatus.OK);

    }
    // cho viec validate update theo id lay cac gia tri cua contract_fee theo code va ngoai tru chinh id do
    @RequestMapping(value = "/getContractFeeOnlyCodeExceptId",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeBO>> getContractFeeOnlyCodeExceptID(@RequestParam final Long codeContract,@RequestParam final int id){

        List<ContractFeeBO> list = new ArrayList<>();
        list = contractFeeService.getContractFeeOnlyCodeExceptId(codeContract,id).orElse(new ArrayList<ContractFeeBO>());
        return new ResponseEntity<List<ContractFeeBO>>(list,HttpStatus.OK);
    }

    /*@RequestMapping(value = "/getContractFeeTcccCall",method = RequestMethod.GET)
    public ResponseEntity<ContractFeeBO> getContractFeeTcccCall(@RequestParam final Long codeContract,@RequestParam final long notaryCost){

        ContractFeeBO result = new ContractFeeBO();

        List<ContractFeeBO> list = contractFeeService.getContractFeeOnlyCode(codeContract).orElse(new ArrayList<ContractFeeBO>());
        result = contractFeeService
        return new ResponseEntity<ContractFeeBO>(list,HttpStatus.OK);
    }*/


    @RequestMapping(value = "/getContractFeeTcccCall",method = RequestMethod.GET)
    public ResponseEntity<ContractFeeBO> getContractFeeTcccCall(@RequestParam final Long codeContract,@RequestParam final long notaryCost){
        ContractFeeBO result = new ContractFeeBO();
        List<ContractFeeBO> list = new ArrayList<>();
        list = contractFeeService.getContractFeeTcccCall(codeContract,notaryCost).orElse(new ArrayList<ContractFeeBO>());
        if(list.size()==1){
            result = list.get(0);
        }else {
            LOG.error("Tìm ra 2 khoảng tinh phi cho hop dong or khong co gia tri nao . Co loi trong database bang npo_contract_fee !");
        }
        return new ResponseEntity<ContractFeeBO>(result,HttpStatus.OK);
    }

    // lay list va phan trang inner join contract_template de tim kiem cho tccc
    @RequestMapping(value = "/getContractFeeCodeJoinContractTemplate",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeCodeTemplate>> getContractFeeCodeJoinContractTemplate(@RequestParam final int page,@RequestParam final String codeContract,@RequestParam final String codeKind){

        List<ContractFeeCodeTemplate> list = new ArrayList<>();
        list = contractFeeService.getContractFeeCodeJoinContractTemplate(page,codeContract,codeKind).orElse(new ArrayList<ContractFeeCodeTemplate>());
        return new ResponseEntity<List<ContractFeeCodeTemplate>>(list,HttpStatus.OK);

    }
    // count inner join contract_template cho tccc
    @RequestMapping(value = "/countContractFeeAllInnerJoinContractTemplate",method = RequestMethod.GET)
    public ResponseEntity<Long> countContractFeeAllInnerJoinContractTemplate(@RequestParam final String codeContract,@RequestParam final String codeKind){
        return new ResponseEntity<Long>(contractFeeService.countContractFeeAllInnerJoinContractTemplate(codeContract,codeKind).orElse(Long.valueOf(0)),HttpStatus.OK);
    }

    @RequestMapping(value = "/getTest",method = RequestMethod.GET)
    public ResponseEntity<List<ContractFeeCodeTemplate>> getTest(){
        List<ContractFeeCodeTemplate> list = new ArrayList<>();
        list = contractFeeService.getTest().orElse(new ArrayList<ContractFeeCodeTemplate>());
        return new ResponseEntity<List<ContractFeeCodeTemplate>>(list,HttpStatus.OK);

    }





}
