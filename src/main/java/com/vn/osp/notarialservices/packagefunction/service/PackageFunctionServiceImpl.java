package com.vn.osp.notarialservices.packagefunction.service;

import com.vn.osp.notarialservices.packagefunction.domain.PackageFunctionBO;
import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import com.vn.osp.notarialservices.packagefunction.repository.PackageFunctionRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PackageFunctionServiceImpl implements PackageFunctionService {
    private static final Logger logger = LogManager.getLogger(PackageFunctionServiceImpl.class);

    private final PackageFunctionRepository packageFunctionRepository;
    private final PackageFunctionConverter packageFunctionConverter;

    @Autowired
    public PackageFunctionServiceImpl(PackageFunctionRepository packageFunctionRepository, PackageFunctionConverter packageFunctionConverter) {
        this.packageFunctionRepository = packageFunctionRepository;
        this.packageFunctionConverter = packageFunctionConverter;
    }

    @Override
    public Optional<Boolean> insert(String package_code,String function_code){
//            PackageFunctionBO packageFunctionBO  = new PackageFunctionBO();
//            packageFunctionBO = packageFunctionConverter.convert(package_code,);
            return packageFunctionRepository.insert(package_code,function_code);
    }
//    @Override
//    public Optional<Boolean> update(PackageFunctionsDTO packageFunctionsDTO){
//        PackageFunctionBO packageFunctionBO  = new PackageFunctionBO();
//        packageFunctionBO = packageFunctionConverter.convert(packageFunctionsDTO);
//        return packageFunctionRepository.update(packageFunctionBO);
//    }
    @Override
    public Optional<Boolean> deleteById(Long id){
        return packageFunctionRepository.deleteById(id);
    }
    @Override
    public String packageCodeMax(){
        return packageFunctionRepository.packageCodeMax();
    }
    @Override
    public Optional<List<PackageFunctionsDTO>> selectPackageFunctionbyFilter(String stringFilter){
        List<PackageFunctionsDTO> list =  packageFunctionRepository.selectPackageFunctionbyFilter(stringFilter)
                .map(packageFunctionConverter::converts).orElse(null);
        return Optional.of(list);
    }
}
