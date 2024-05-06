package com.vn.osp.notarialservices.mail.service;

import com.vn.osp.notarialservices.mail.dto.MailSendList;
import com.vn.osp.notarialservices.mail.dto.MailTLSSendList;
import com.vn.osp.notarialservices.utils.SendMailUtil;

import java.util.Optional;

/**
 * Created by Admin on 6/1/2018.
 */
public interface MailService {
    Optional<Boolean> sendMail(MailSendList mailSendList);
    Optional<Boolean> sendMailNewPass(MailTLSSendList mailTLSSendList);

}
