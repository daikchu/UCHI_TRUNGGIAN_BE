package com.vn.osp.notarialservices.packagefunction.service;


import com.vn.osp.notarialservices.common.converter.Converter;
import com.vn.osp.notarialservices.packagefunction.domain.PackageFunctionBO;
import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageFunctionConverter implements Converter<PackageFunctionBO, PackageFunctionsDTO> {

    @Override
    public PackageFunctionsDTO convert(final PackageFunctionBO source) {
        return new PackageFunctionsDTO(
                source.getId(),
                source.getPackage_code(),
                source.getFunction_code());
    }

//    public Timestamp convertStringToTimeStamp(String dateString){
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date parsedDate = null;
//            parsedDate = dateFormat.parse(dateString);
//            Timestamp timestamp = new Timestamp(parsedDate.getTime());
//            return  timestamp;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public String convertTimeStampToString(Timestamp date){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        if(date==null){
//            return null;
//        }
//        else {
//            String result = dateFormat.format(date);
//            return result;
//        }
//    }

    public List<PackageFunctionsDTO> converts(final List<PackageFunctionBO> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
        public PackageFunctionBO convert(final PackageFunctionsDTO source) {
        PackageFunctionBO packageFunctionBO = new PackageFunctionBO();
        packageFunctionBO.setId(source.getSId());
        packageFunctionBO.setPackage_code(source.getPackage_code());
        packageFunctionBO.setFunction_code(source.getFunction_code());
        return packageFunctionBO;
    }

}
