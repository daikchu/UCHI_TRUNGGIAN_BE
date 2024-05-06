package com.vn.osp.notarialservices.function.service;

import com.vn.osp.notarialservices.customer.controller.CustomerController;
import com.vn.osp.notarialservices.customer.repository.CustomerRepository;
import com.vn.osp.notarialservices.function.domain.FunctionBO;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.function.repository.FunctionRepository;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;
import com.vn.osp.notarialservices.packagefunction.service.GroupRoleFunctionTemplateConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FunctionServiceImpl implements FunctionService {
    private static final Logger logger = LogManager.getLogger(CustomerController.class);

//    @PersistenceContext
//    private EntityManager entityManager;
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
    private final FunctionConverter functionConverter;
    private final FunctionRepository functionRepository;
    private final GroupRoleFunctionTemplateConverter groupRoleFunctionTemplateConverter;
    @Autowired
    public FunctionServiceImpl(FunctionRepository functionRepository,FunctionConverter functionConverter,GroupRoleFunctionTemplateConverter groupRoleFunctionTemplateConverter){
        this.functionRepository = functionRepository;
        this.functionConverter = functionConverter;
        this.groupRoleFunctionTemplateConverter = groupRoleFunctionTemplateConverter;
    }

    @Override
    public Optional<List<FunctionDTO>> getByFunction(String stringFilter) {
        List<FunctionDTO> listCustomer =  functionRepository.getByFunction(stringFilter)
                .map(functionConverter::converts).orElse(null);
        return Optional.of(listCustomer);
//        try{
//            Query query = entityManager.createNativeQuery("SELECT fu.code \n" +
//                    "FROM npo_customer cu, npo_package pa, npo_package_function pf, npo_function fu \n" +
//                    "WHERE cu.pakage_code = pa.code = pf.package_code \n" +
//                    "AND pf.function_code = fu.code \n" +
//                    "and cu.code=:code");
//            query.setParameter("code", stringFilter);
//            listBO = query.getResultList();
//            if (listBO != null && listBO.size()>0) {
//                for (int i = 0; i < listBO.size(); i++) {
//                    result.add(Optional.ofNullable(listBO.get(i)).map(functionConverter::convert).orElse(new FunctionDTO()));
//                }
//            }
//        }catch (Exception e){
//            logger.error("Have an error in method getByFunction:" + e.getMessage());
//        }

    }
//    @Override
//    public Optional<List<FunctionDTO>> getFunctionByFilter(String stringFilter){
////        Optional<List<FunctionBO>> functionBOS = functionRepository.getFunctionByFilter(stringFilter);
////        List<FunctionDTO> listCustomer = functionConverter.converts(functionBOS);
//        List<FunctionDTO> listCustomer =  functionRepository.getFunctionByFilter(stringFilter).map(functionConverter::converts).orElse(null);
//        return Optional.of(listCustomer);
//    }

    @Override
    public Optional<List<FunctionDTO>> getFunctionByFilter(String stringFilter) {

        List<FunctionBO> listBO = functionRepository.getFunctionByFilter(stringFilter).orElse(new ArrayList<FunctionBO>());
        ArrayList<FunctionDTO> list = new ArrayList<FunctionDTO>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(functionConverter::convert).orElse(new FunctionDTO()));
            }
        }
        return Optional.of(list);
    }

    @Override
    public Optional<List<GroupRoleFunctionTemplateDTO>> getGroupRoleFunctionTemplateDTOByFilter(String stringFilter){
//        List<GroupRoleFunctionTemplateBO> functionTemplateBOS =  functionRepository.getGroupRoleFunctionTemplateDTOByFilter(stringFilter);
//        List<GroupRoleFunctionTemplateDTO> list = functionConverter.convertsToDTO(functionTemplateBOS);
        List<GroupRoleFunctionTemplateDTO> list =  functionRepository.getGroupRoleFunctionTemplateDTOByFilter(stringFilter)
                .map(groupRoleFunctionTemplateConverter::converts).orElse(null);
        return Optional.of(list);
    }


}
