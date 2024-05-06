package com.vn.osp.notarialservices.mail.service;

import com.vn.osp.notarialservices.mail.dto.MailSendList;
import com.vn.osp.notarialservices.mail.dto.MailTLSSendList;
import com.vn.osp.notarialservices.utils.SendMailTLS;
import com.vn.osp.notarialservices.utils.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Admin on 6/1/2018.
 */
@Component
public class MailServiceImpl implements MailService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendList.class);
    @Override
    public Optional<Boolean> sendMail(MailSendList mailSendList)
    {
        try{
            SendMailUtil.sendGmail(mailSendList.getTo(),mailSendList.getTitle(),mailSendList.getContent());
        }catch (Exception e){
            return Optional.of(Boolean.valueOf(false));
        }
        return Optional.of(Boolean.valueOf(true));

    }

    public Optional<Boolean> sendMailNewPass(MailTLSSendList mailTLSSendList)
    {
        boolean mail;
        try{
            mail = SendMailTLS.mail(mailTLSSendList.getEmail(),mailTLSSendList.getNewPass(),mailTLSSendList.getAccount(),mailTLSSendList.getLinkLogin());
        }catch (Exception e){
            return Optional.of(Boolean.valueOf(false));
        }
        if(mail == false){
            return Optional.of(Boolean.valueOf(false));
        }
        return Optional.of(Boolean.valueOf(true));

    }


}
