package com.vn.osp.notarialservices.paymentTransaction.service;

import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.paymentTransaction.repository.OrderMapVerifyRepository;
import com.vn.osp.notarialservices.utils.GenerateIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrderMapVerificationServiceImpl implements OrderMapVerificationService{
    @Autowired
    private OrderMapVerifyRepository orderMapVerifyRepository;
    @Override
    public Optional<OrderMapVerification> saveOrUpdate(OrderMapVerification orderMapVerification){
         return orderMapVerifyRepository.saveOrUpdate(orderMapVerification);
   }
   @Override
   public List<OrderMapVerification> orderIdMapVerification(String orderId, int verifyNumber){
        List<OrderMapVerification> list = new ArrayList<>();
       List<OrderMapVerification> result = new ArrayList<>();
        if(verifyNumber > 0){
            for(int i = 0; i<verifyNumber; i++){
                OrderMapVerification map = new OrderMapVerification();
                map.setOrder_id(orderId);
                OrderMapVerification orderMapVerification = orderMapVerifyRepository.saveOrUpdate(map).orElse(null);
                if(orderMapVerification.getId() > 0){
                    list.add(orderMapVerification);
                }
            }
            if(!list.isEmpty()){
                for(OrderMapVerification bo : list){
                    // generate mã đơn hàng verifyId
                    // công thức generate: XT + 2 chữ cái lấy theo bảng chữ cái tiếng anh + 6 chữ số được tạo tăng dần từ 1-999999
                    bo.setVerify_id(GenerateIdUtil.generateId("XT", bo.getId().intValue()));
                    bo.setVerify_status(0);
                    OrderMapVerification mapVerification = orderMapVerifyRepository.saveOrUpdate(bo).orElse(null);
                    if(mapVerification.getId() > 0){
                        result.add(mapVerification);
                    }
                }
            }
        }
       return result;
   }
}
