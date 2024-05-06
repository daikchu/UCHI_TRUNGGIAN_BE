package com.vn.osp.notarialservices.Package.service;

import com.vn.osp.notarialservices.Package.controller.PackageController;
import com.vn.osp.notarialservices.Package.domain.PackageBO;
import com.vn.osp.notarialservices.Package.dto.PackageDTO;
import com.vn.osp.notarialservices.Package.dto.PackageFuncDTO;
import com.vn.osp.notarialservices.Package.repository.PackageRepository;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.service.CustomerServiceImpl;
import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import com.vn.osp.notarialservices.packagefunction.service.PackageFunctionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PackageServiceImpl implements PackageService  {
    private static final Logger logger = LogManager.getLogger(PackageServiceImpl.class);
    private final PackageRepository packageRepository;
    private final PackageConverter packageConverter;
    private final PackageFunctionService packageFunctionService;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, PackageConverter packageConverter,PackageFunctionService packageFunctionService) {
        this.packageRepository = packageRepository;
        this.packageConverter = packageConverter;
        this.packageFunctionService = packageFunctionService;
    }
    @Override
    public  Optional<Boolean> insert(PackageFuncDTO packageFuncDTO) {
        try{
            PackageBO packageBO = new PackageBO();
            packageBO.setCode(packageFuncDTO.getCode());
            packageBO.setName(packageFuncDTO.getName());
            packageBO.setUser_num(packageFuncDTO.getUser_num());
            packageBO.setDescription(packageFuncDTO.getDescription());
            packageBO.setNote(packageFuncDTO.getNote());
            packageRepository.insert(packageBO);
            // insert dữ liệu vào bảng npo_package_code
            String package_code = packageBO.getCode();
            String systemManagerCheckList = packageFuncDTO.getCb01();
            String functionCheckList = packageFuncDTO.getCb02();
            String reportCheckList = packageFuncDTO.getCb03();

            if (systemManagerCheckList != null && !systemManagerCheckList.equals("null")) {
                String[] array = systemManagerCheckList.split(",");
                String functionSystem = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (functionSystem == "" || (!functionSystem.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        functionSystem = result[0];
                    }
                }
            }

            if (functionCheckList != null && !functionCheckList.equals("null")) {
                String[] array = functionCheckList.split(",");
                String function = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (function == "" || (!function.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        function = result[0];
                    }
                }
            }

            if (reportCheckList != null && !reportCheckList.equals("null")) {
                String[] array = reportCheckList.split(",");
                String report = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (report == "" || (!report.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        report = result[0];
                    }
                }
            }
            return Optional.of(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        return Optional.of(false);
       }
    }

    @Override
    public Optional<Boolean> update(PackageFuncDTO packageFuncDTO) {
        try{
            Optional<Boolean> resultStatus = Optional.of(false);
            PackageBO packageBO = new PackageBO();
            packageBO.setId(packageFuncDTO.getId());
            packageBO.setCode(packageFuncDTO.getCode());
            packageBO.setName(packageFuncDTO.getName());
            packageBO.setUser_num(packageFuncDTO.getUser_num());
            packageBO.setDescription(packageFuncDTO.getDescription());
            packageBO.setNote(packageFuncDTO.getNote());
            packageRepository.update(packageBO);
            // update dữ liệu vào bảng npo_package_code
            String package_code = packageBO.getCode();
            String filter1 = " where package_code=";
            filter1 += "'" + package_code + "'";
            List<PackageFunctionsDTO> functionDTOList = packageFunctionService.selectPackageFunctionbyFilter(filter1).orElse(new ArrayList<>());
            for (int i = 0; i < functionDTOList.size(); i++) {
                PackageFunctionsDTO packageFunctionsDTO = new PackageFunctionsDTO();
                packageFunctionsDTO.setSId(functionDTOList.get(i).getSId());
                // Xóa dữ liệu bảng npo_package_function dựa vào packagecode
                resultStatus = packageFunctionService.deleteById(packageFunctionsDTO.getSId());
            }

            String systemManagerCheckList = packageFuncDTO.getCb01();
            String functionCheckList = packageFuncDTO.getCb02();
            String reportCheckList = packageFuncDTO.getCb03();

            if (systemManagerCheckList != null && !systemManagerCheckList.equals("null")) {
                String[] array = systemManagerCheckList.split(",");
                String functionSystem = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (functionSystem == "" || (!functionSystem.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        functionSystem = result[0];
                    }
                }
            }

            if (functionCheckList != null && !functionCheckList.equals("null")) {
                String[] array = functionCheckList.split(",");
                String function = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (function == "" || (!function.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        function = result[0];
                    }
                }
            }

            if (reportCheckList != null && !reportCheckList.equals("null")) {
                String[] array = reportCheckList.split(",");
                String report = "";
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    if (report == "" || (!report.equals(result[0]))) {
                        packageFunctionService.insert(package_code, result[0]);
                        report = result[0];
                    }
                }
            }
            return resultStatus;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }


    @Override
    public Optional<Boolean> deleteById(Long id) {
        Optional<Boolean> result = Optional.of(false);
        String filter = " where id = " + id;
        List<PackageBO> packageBOS = packageRepository.selectPackagebyFilter(filter).orElse(new ArrayList<>());
        String packagecode = packageBOS.get(0).getCode();
        result = packageRepository.deleteById(id);
        String filter1 = " where package_code=";
        filter1 += "'" + packagecode + "'";
        List<PackageFunctionsDTO> functionDTOList = packageFunctionService.selectPackageFunctionbyFilter(filter1).orElse(new ArrayList<>());
        for (int i = 0; i < functionDTOList.size(); i++) {
            PackageFunctionsDTO packageFunctionsDTO = new PackageFunctionsDTO();
            packageFunctionsDTO.setSId(functionDTOList.get(i).getSId());
            // Xóa dữ liệu bảng npo_package_function dựa vào packagecode
            result = packageFunctionService.deleteById(packageFunctionsDTO.getSId());
        }
        return result;
    }

    @Override
    public Optional<BigInteger> packageCountTotalByFilter(String stringFilter){
        return packageRepository.packageCountTotalByFilter(stringFilter);
    }
    @Override
    public Optional<List<PackageDTO>> selectPackagebyFilter(String stringFilter){
        List<PackageDTO> list =  packageRepository.selectPackagebyFilter(stringFilter)
                .map(packageConverter::converts).orElse(null);
        return Optional.of(list);
    }
}

