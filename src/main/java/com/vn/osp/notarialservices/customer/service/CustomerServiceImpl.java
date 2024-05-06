package com.vn.osp.notarialservices.customer.service;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.repository.CitizenVerficationNumberRepository;
import com.vn.osp.notarialservices.citizenVerificationOrder.repository.CitizenVerificationOrderRepository;
import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.citizenVerifyPackage.service.CitizenVerifyPackageService;
import com.vn.osp.notarialservices.customer.domain.CustomerBO;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.dto.CustomerInfoDTO;
import com.vn.osp.notarialservices.customer.dto.RoleFunctionCustomerDTO;
import com.vn.osp.notarialservices.customer.repository.CustomerRepository;
import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.function.dto.PackageFunctionDTO;
import com.vn.osp.notarialservices.function.service.FunctionConverter;
import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import com.vn.osp.notarialservices.packagefunction.repository.PackageFunctionRepository;
import com.vn.osp.notarialservices.packagefunction.service.PackageFunctionService;
import com.vn.osp.notarialservices.province.domain.ProvinceBo;
import com.vn.osp.notarialservices.province.dto.Province;
import com.vn.osp.notarialservices.user.dto.GroupRole;
import com.vn.osp.notarialservices.user.repository.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
//    private final UserRepository userRepository;
    private final PackageFunctionService packageFunctionService;
    @Autowired
    private CitizenVerifyPackageService citizenVerifyPackageService;
    @Autowired
    private CitizenVerficationNumberRepository citizenVerficationNumberRepository;

    //    private final PackageFunctionRepository packageFunctionRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter, PackageFunctionService packageFunctionService) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
//        this.packageFunctionRepository = packageFunctionRepository;
        this.packageFunctionService = packageFunctionService;
    }

    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    FunctionConverter functionConverter;

    @Override
    public Optional<Boolean> insert(CustomerDTO customerDTO) {
        try {
            CustomerBO customerBO = new CustomerBO();
            customerBO.setActive_flg(customerDTO.getActive_flg());
            customerBO.setAddress(customerDTO.getAddress());
            customerBO.setCode(customerDTO.getCode());
            customerBO.setEmail(customerDTO.getEmail());
            customerBO.setMac_address(customerDTO.getMac_address());
            customerBO.setIp_address(customerDTO.getIp_address());
            customerBO.setName(customerDTO.getName());
            customerBO.setOffice_type(customerDTO.getOffice_type());
            customerBO.setPhone(customerDTO.getPhone());
            customerBO.setWebsite(customerDTO.getWebsite());
            customerBO.setDomain(customerDTO.getDomain());
            customerBO.setIs_demo(customerDTO.getIs_demo());
            customerBO.setPakage_code(customerDTO.getPakage_code());
            customerBO.setDate_start(customerConverter.convertStringToTimeStamp(customerDTO.getDate_start()));
            customerBO.setDate_exp(customerConverter.convertStringToTimeStamp(customerDTO.getDate_exp()));
            customerBO.setMessage_show(customerDTO.getMessage_show());
            customerBO.setMessage_time_to_show(customerDTO.getMessage_time_to_show());
            customerBO.setMessage_active_flg(customerDTO.getMessage_active_flg());
            customerBO.setType(customerDTO.getType());
            customerBO.setDescription(customerDTO.getDescription());
            customerBO.setProvince_code(customerDTO.getProvince_code());
            customerBO.setNote(customerDTO.getNote());
            customerBO.setCitizen_verify_package_code(customerDTO.getCitizen_verify_package_code());

//            String package_code = customerBO.getPakage_code();


//            // insert dữ liệu vào bảng npo_package_code
//            String systemManagerCheckList = customerDTO.getCb01();
//            String functionCheckList = customerDTO.getCb02();
//            String reportCheckList = customerDTO.getCb03();
//
//            String packagecode = packageFunctionService.packageCodeMax();
//            int codePackageAdd = Integer.valueOf(packagecode) + 1;
//            String package_codeAdd = String.valueOf(codePackageAdd);
////            String packagecode = "01";
////             insert dữ liệu vào bảng npo_package_code
////             insert theo function type = 1
//            if (systemManagerCheckList != null && !systemManagerCheckList.equals("null")) {
//                String[] array = systemManagerCheckList.split(",");
//                int a = 0;
////                boolean checkAdd = false;
//                String functionSystem = "";
//                for (int i = 0; i < array.length; i++) {
//                    String[] result = array[i].split("_");
////                    if(checkAdd == false){
//                    if (functionSystem == "" || (!functionSystem.equals(result[0]))) {
//                        int temp = Integer.valueOf(result[1]).intValue();
//                        int codePackage = Integer.valueOf(packagecode) + 1;
//                        String package_code = String.valueOf(codePackage);
//                        packageFunctionService.insert(package_code, result[0]);
//                        functionSystem = result[0];
////                        checkAdd = true;
//                    }
//
//
//                }
//            }
//            if (functionCheckList != null && !functionCheckList.equals("null")) {
//                String[] array = functionCheckList.split(",");
//                int a = 0;
//                String function = "";
//                for (int i = 0; i < array.length; i++) {
//                    String[] result = array[i].split("_");
//                    if (function == "" || (!function.equals(result[0]))) {
//                        int codePackage1 = Integer.valueOf(packagecode) + 1;
//                        String package_code1 = String.valueOf(codePackage1);
//                        packageFunctionService.insert(package_code1, result[0]);
//                        function = result[0];
//                    }
//                }
//            }
//
//            if (reportCheckList != null && !reportCheckList.equals("null")) {
//                String[] array = reportCheckList.split(",");
//                int a = 0;
//                String report = "";
//                for (int i = 0; i < array.length; i++) {
//                    String[] result = array[i].split("_");
//                    if (report == "" || (!report.equals(result[0]))) {
//                        int codePackage2 = Integer.valueOf(packagecode) + 1;
//                        String package_code2 = String.valueOf(codePackage2);
//                        packageFunctionService.insert(package_code2, result[0]);
//                        report = result[0];
//                    }
//                }
//            }
            customerRepository.insert(customerBO);

            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> update(CustomerDTO customerDTO) {
        try {
            CustomerBO customerBO = new CustomerBO();
            customerBO.setId(customerDTO.getNoid());
            customerBO.setActive_flg(customerDTO.getActive_flg());
            customerBO.setAddress(customerDTO.getAddress());
            customerBO.setCode(customerDTO.getCode());
            customerBO.setEmail(customerDTO.getEmail());
            customerBO.setMac_address(customerDTO.getMac_address());
            customerBO.setIp_address(customerDTO.getIp_address());
            customerBO.setName(customerDTO.getName());
            customerBO.setOffice_type(customerDTO.getOffice_type());
            customerBO.setPhone(customerDTO.getPhone());
            customerBO.setWebsite(customerDTO.getWebsite());
            customerBO.setDomain(customerDTO.getDomain());
            customerBO.setIs_demo(customerDTO.getIs_demo());
            customerBO.setPakage_code(customerDTO.getPakage_code());
            customerBO.setDate_start(customerConverter.convertStringToTimeStamp(customerDTO.getDate_start()));
            customerBO.setDate_exp(customerConverter.convertStringToTimeStamp(customerDTO.getDate_exp()));
            customerBO.setMessage_show(customerDTO.getMessage_show());
            customerBO.setMessage_time_to_show(customerDTO.getMessage_time_to_show());
            customerBO.setMessage_active_flg(customerDTO.getMessage_active_flg());
            customerBO.setType(customerDTO.getType());
            customerBO.setDescription(customerDTO.getDescription());
            customerBO.setProvince_code(customerDTO.getProvince_code());
            customerBO.setNote(customerDTO.getNote());
            customerBO.setCitizen_verify_package_code(customerDTO.getCitizen_verify_package_code());
            customerRepository.update(customerBO);
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        return customerRepository.deleteById(id);
    }

    @Override
    public Optional<List<CustomerDTO>> selectByFilter(String stringFilter) {
        List<CustomerDTO> listCustomer = customerRepository.selectByFilter(stringFilter)
                .map(customerConverter::converts).orElse(null);
        return Optional.of(listCustomer);
    }
    @Override
    public Optional<List<CustomerDTO>> getCustomerByCode(String stringFilter) {
        List<CustomerDTO> listCustomer = customerRepository.getCustomerByCode(stringFilter)
                .map(customerConverter::converts).orElse(null);
        return Optional.of(listCustomer);
    }


    @Override
    public Optional<BigInteger> countTotalCustomer(String stringFilter) {
        return customerRepository.countTotalCustomer(stringFilter);
    }

    @Override
    public CustomerInfoDTO getCustomerInfoByCode(String code) {

        CustomerInfoDTO result = new CustomerInfoDTO();

        try {

            CustomerBO customerBO = new CustomerBO();

            Query query = entityManager.createQuery("SELECT cu FROM CustomerBO cu WHERE cu.code =:code");
            customerBO = (CustomerBO) query.setParameter("code", code).getSingleResult();


            result.setCode(customerBO.getCode());
            result.setName(customerBO.getName());
            result.setWebsite(customerBO.getWebsite());
            result.setDomain(customerBO.getDomain());
            result.setPakage_code(customerBO.getPakage_code());
            result.setDate_start(customerBO.getDate_start());
            result.setDate_exp(customerBO.getDate_exp());
            result.setMessage_show(customerBO.getMessage_show());
            result.setMessage_time_to_show(customerBO.getMessage_time_to_show());
            result.setMessage_active_flg(customerBO.getMessage_active_flg());
            result.setProvince_code(customerBO.getProvince_code());
            result.setCitizen_verify_package_code(customerBO.getCitizen_verify_package_code());


            String hql = "SELECT pf.package_code, fu.* " +
                    "FROM npo_customer cu, npo_package pa, npo_package_function pf, npo_function fu " +
                    "WHERE cu.pakage_code = pa.code = pf.package_code " +
                    "AND pf.function_code = fu.code " +
                    "and cu.code='" + code + "'";
            List<Object[]> listPF = new ArrayList<>();
            listPF = entityManager.createNativeQuery(hql).getResultList();

            List<PackageFunctionDTO> packageFunctionDTO = new ArrayList<PackageFunctionDTO>();

            if (listPF != null && listPF.size() > 0) {
                for (int i = 0; i < listPF.size(); i++) {
                    PackageFunctionDTO pkFunction = new PackageFunctionDTO();
                    pkFunction.setPackage_code((String) listPF.get(i)[0]);
                    pkFunction.setActive_flg(1);
                    pkFunction.setCode((String) listPF.get(i)[1]);
                    pkFunction.setName((String) listPF.get(i)[2]);
                    pkFunction.setParent_code((String) listPF.get(i)[3]);
                    pkFunction.setType((int) listPF.get(i)[4]);

                    packageFunctionDTO.add(pkFunction);
                }
                result.setFunctionDTOList(packageFunctionDTO);
            }

            CitizenVerifyNumberBO citizenVerifyNumberBO =
                    citizenVerficationNumberRepository.get(customerBO.getCode(), customerBO.getProvince_code()).orElse(null);
            CitizenVerifyPackageBO citizenVerifyPackageBO =
                    citizenVerifyPackageService.getByCode(customerBO.getCitizen_verify_package_code()).orElse(null);
            result.setCitizenVerifyNumber(citizenVerifyNumberBO);
            result.setCitizen_verify_package(citizenVerifyPackageBO);

        } catch (Exception e) {
            logger.error("Have an error in method getCustomerInfoByCode:" + e.getMessage());
        }
        return result;
    }

}
