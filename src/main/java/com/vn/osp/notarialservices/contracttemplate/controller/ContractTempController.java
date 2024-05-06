package com.vn.osp.notarialservices.contracttemplate.controller;

import com.vn.osp.notarialservices.contracttemplate.domain.PrivyTemplateBO;
import com.vn.osp.notarialservices.contracttemplate.domain.PropertyTemplateBO;
import com.vn.osp.notarialservices.contracttemplate.dto.AddContractTemp;
import com.vn.osp.notarialservices.contracttemplate.dto.ContractTemp;
import com.vn.osp.notarialservices.contracttemplate.repository.PrivyTemplateRepositoryCustom;
import com.vn.osp.notarialservices.contracttemplate.repository.PropertyTemplateRepositoryCustom;
import com.vn.osp.notarialservices.contracttemplate.service.ContractTempService;
import com.vn.osp.notarialservices.contracttemplate.service.PropertyTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/6/2017.
 */
@Controller
@RequestMapping(value = "/ContractTemplate")
public class ContractTempController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTempController.class);

    private final ContractTempService contractTempService;
    private final ContractTempHateoasBuilder contractTempHateoasBuilder;
    private final PropertyTemplateService propertyTemplateService;
    private final PropertyTemplateRepositoryCustom propertyTemplateRepositoryCustom;
    private final PrivyTemplateRepositoryCustom privyTemplateRepositoryCustom;

    @Autowired
    public ContractTempController(final ContractTempService contractTempService, final ContractTempHateoasBuilder contractTempHateoasBuilder,
                                  final PropertyTemplateService propertyTemplateService, final PropertyTemplateRepositoryCustom propertyTemplateRepositoryCustom,
                                  final PrivyTemplateRepositoryCustom privyTemplateRepositoryCustom){
        this.contractTempService = contractTempService;
        this.contractTempHateoasBuilder = contractTempHateoasBuilder;
        this.propertyTemplateService = propertyTemplateService;
        this.propertyTemplateRepositoryCustom = propertyTemplateRepositoryCustom;
        this.privyTemplateRepositoryCustom=privyTemplateRepositoryCustom;
    }
    @RequestMapping(value = "/AddContractTemp", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addContractTemp(@RequestBody @Valid final AddContractTemp addContractTemp)  {
        return new ResponseEntity<Boolean>((Boolean) contractTempService.addContractTemp(addContractTemp).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/UpdateContractTemp", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateContractTemp(@RequestBody @Valid final ContractTemp item)  {
        return new ResponseEntity<Boolean>((Boolean) contractTempService.updateContractTemp(item).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/DeleteContractTemp", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteContractTemp(@RequestBody @Valid final Long id)  {
        return new ResponseEntity<Boolean>((Boolean) contractTempService.deleteContractTemp(id).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value="/FindContractTempByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<ContractTemp>> findContractTempByFilter(@RequestBody final String stringFilter)  {
        List<ContractTemp> contractTempByFilter = contractTempService.findContractTempByFilter(stringFilter).orElse(new ArrayList<ContractTemp>());

        return new ResponseEntity<List<ContractTemp>>(contractTempByFilter, HttpStatus.OK);
    }
    @RequestMapping(value="/CountContractTempByFilter", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> countContractTempByFilter(@RequestBody final String stringFilter)  {
        BigInteger contractTempByFilter = contractTempService.countContractTempByFilter(stringFilter).orElse(BigInteger.valueOf(0));

        return new ResponseEntity<BigInteger>(contractTempByFilter, HttpStatus.OK);
    }

    @RequestMapping(value="/checkByCode", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkByCode(@RequestParam final String code)  {
        Boolean check = contractTempService.getByCode(code).orElse(true);
        return new ResponseEntity<Boolean>(check, HttpStatus.OK);
    }

    /*property template controller*/
    @RequestMapping(value = "/property-template", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyTemplateBO>> getPropertyTemplate() {
        List<PropertyTemplateBO> list=new ArrayList<>();
        try{
            list= propertyTemplateRepositoryCustom.list().orElse(new ArrayList<>());
            return new ResponseEntity<List<PropertyTemplateBO>>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PropertyTemplateBO>>(list , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template-by-id", method = RequestMethod.GET)
    public ResponseEntity<PropertyTemplateBO> getPropertyTemplateById(@RequestParam  @Valid final int id) {
        PropertyTemplateBO item=new PropertyTemplateBO();
        try{
            item= propertyTemplateRepositoryCustom.getById(id).orElse(new PropertyTemplateBO());
            return new ResponseEntity<PropertyTemplateBO>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PropertyTemplateBO>(item , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template-get-by-type", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyTemplateBO>> getPropertyTemplateByType(@RequestParam  @Valid  final String type) {
        List<PropertyTemplateBO> item=new ArrayList<>();
        try{
            item= propertyTemplateRepositoryCustom.getByType(type).orElse(new ArrayList<>());
            return new ResponseEntity<List<PropertyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PropertyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }

    }
    @RequestMapping(value = "/property-template-total", method = RequestMethod.GET)
    public ResponseEntity<Long> totalPropertyTemplate() {
        try{
            Long item= propertyTemplateRepositoryCustom.total().orElse(Long.valueOf(0));
            return new ResponseEntity<Long>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Long>(Long.valueOf(0) , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template-count-by-type", method = RequestMethod.GET)
    public ResponseEntity<Long> countPropertyTemplateByType(@RequestParam  @Valid  final String type) {
        try{
            Long item= propertyTemplateRepositoryCustom.countByType(type).orElse(Long.valueOf(0));
            return new ResponseEntity<Long>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Long>(Long.valueOf(0) , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template-get-page", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyTemplateBO>> getPropertyTemplatePage( @RequestParam  @Valid  final int offset, @RequestParam  @Valid  final int number) {
        List<PropertyTemplateBO> item=new ArrayList<>();
        try{
            item= propertyTemplateRepositoryCustom.listItemPage(offset,number).orElse(new ArrayList<>());
            return new ResponseEntity<List<PropertyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PropertyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template-get-page-by-type", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyTemplateBO>> getPropertyTemplatePageByType(@RequestParam  @Valid  final String type, @RequestParam  @Valid  final int offset, @RequestParam  @Valid  final int number) {
        List<PropertyTemplateBO> item=new ArrayList<>();
        try{
            item= propertyTemplateRepositoryCustom.listItemPageByType(type,offset,number).orElse(new ArrayList<>());
            return new ResponseEntity<List<PropertyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PropertyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> editPropertyTemplate(@RequestBody  @Valid  final PropertyTemplateBO item) {

        try{
            boolean check= propertyTemplateRepositoryCustom.update(item).orElse(false);
            if(check)
                return new ResponseEntity<Boolean>(true,HttpStatus.OK);
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addPropertyTemplate(@RequestBody  @Valid  final PropertyTemplateBO item) {

        try{
            boolean check= propertyTemplateRepositoryCustom.add(item).orElse(false);
            if(check)
                return new ResponseEntity<Boolean>(true,HttpStatus.OK);
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/property-template", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePropertyTemplate(@RequestParam  @Valid  final int id) {
        if(id==0) return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        try{
            boolean item= propertyTemplateRepositoryCustom.deleteItem(id);
            return new ResponseEntity<Boolean>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    /*privy template controller*/

    @RequestMapping(value = "/privy-template", method = RequestMethod.GET)
    public ResponseEntity<List<PrivyTemplateBO>> getPrivyTemplate() {
        List<PrivyTemplateBO> list=new ArrayList<>();
        try{
            list= privyTemplateRepositoryCustom.list().orElse(new ArrayList<>());
            return new ResponseEntity<List<PrivyTemplateBO>>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PrivyTemplateBO>>(list , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template-by-id", method = RequestMethod.GET)
    public ResponseEntity<PrivyTemplateBO> getPrivyTemplateById(@RequestParam  @Valid final int id) {
        PrivyTemplateBO item=new PrivyTemplateBO();
        try{
            item= privyTemplateRepositoryCustom.getById(id).orElse(new PrivyTemplateBO());
            return new ResponseEntity<PrivyTemplateBO>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PrivyTemplateBO>(item , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template-get-by-type", method = RequestMethod.GET)
    public ResponseEntity<List<PrivyTemplateBO>> getPrivyTemplateByType(@RequestParam  @Valid  final String type) {
        List<PrivyTemplateBO> item=new ArrayList<>();
        try{
            item= privyTemplateRepositoryCustom.getByType(type).orElse(new ArrayList<>());
            return new ResponseEntity<List<PrivyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PrivyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }

    }
    @RequestMapping(value = "/privy-template-total", method = RequestMethod.GET)
    public ResponseEntity<Long> totalPrivyTemplate() {
        try{
            Long item= privyTemplateRepositoryCustom.total().orElse(Long.valueOf(0));
            return new ResponseEntity<Long>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Long>(Long.valueOf(0) , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template-count-by-type", method = RequestMethod.GET)
    public ResponseEntity<Long> countPrivyTemplateByType(@RequestParam  @Valid  final String type) {
        try{
            Long item= privyTemplateRepositoryCustom.countByType(type).orElse(Long.valueOf(0));
            return new ResponseEntity<Long>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Long>(Long.valueOf(0) , HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/privy-template-get-page", method = RequestMethod.GET)
    public ResponseEntity<List<PrivyTemplateBO>> getPrivyTemplatePage( @RequestParam  @Valid  final int offset, @RequestParam  @Valid  final int number) {
        List<PrivyTemplateBO> item=new ArrayList<>();
        try{
            item= privyTemplateRepositoryCustom.listItemPage(offset,number).orElse(new ArrayList<>());
            return new ResponseEntity<List<PrivyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PrivyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/privy-template-get-page-by-type", method = RequestMethod.GET)
    public ResponseEntity<List<PrivyTemplateBO>> getPrivyTemplatePageByType(@RequestParam  @Valid  final String type, @RequestParam  @Valid  final int offset, @RequestParam  @Valid  final int number) {
        List<PrivyTemplateBO> item=new ArrayList<>();
        try{
            item= privyTemplateRepositoryCustom.listItemPageByType(type,offset,number).orElse(new ArrayList<>());
            return new ResponseEntity<List<PrivyTemplateBO>>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<PrivyTemplateBO>>(item , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> editPrivyTemplate(@RequestBody  @Valid  final PrivyTemplateBO item) {

        try{
            boolean check= privyTemplateRepositoryCustom.update(item).orElse(false);
            if(check)
                return new ResponseEntity<Boolean>(true,HttpStatus.OK);
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addPrivyTemplate(@RequestBody  @Valid  final PrivyTemplateBO item) {

        try{
            boolean check= privyTemplateRepositoryCustom.add(item).orElse(false);
            if(check)
                return new ResponseEntity<Boolean>(true,HttpStatus.OK);
            return new ResponseEntity<Boolean>(false,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/privy-template", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePrivyTemplate(@RequestParam  @Valid  final int id) {
        if(id==0) return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        try{
            boolean item= privyTemplateRepositoryCustom.deleteItem(id);
            return new ResponseEntity<Boolean>(item,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false , HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/getContractTempOsp", method = RequestMethod.POST)
    public ResponseEntity<List<ContractTemp>> getContractTempVpcc(@RequestBody final String id){

        String stringFilter = "where id = " + id;
        List<ContractTemp> contractTempByFilter = contractTempService.findContractTempByFilter(stringFilter).orElse(new ArrayList<ContractTemp>());
        return  new ResponseEntity<List<ContractTemp>>(contractTempByFilter, HttpStatus.OK);
    }

}
