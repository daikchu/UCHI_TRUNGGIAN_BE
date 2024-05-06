package com.vn.osp.notarialservices.mail.controller;

import com.vn.osp.notarialservices.mail.dto.MailSendList;
import com.vn.osp.notarialservices.mail.dto.MailTLSSendList;
import com.vn.osp.notarialservices.mail.service.MailService;
import com.vn.osp.notarialservices.utils.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Admin on 6/1/2018.
 */
@Controller
@RequestMapping(value = "/mail")
public class MailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailUtil.class);
    private final MailService mailService;


    @Autowired
    public MailController(final MailService mailService){
        this.mailService = mailService;

    }
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendMail(@RequestBody @Valid final MailSendList mailSendList)  {
        return new ResponseEntity<Boolean>((Boolean) mailService.sendMail(mailSendList).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value= "/send-newpass", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendMailNewPass(@RequestBody @Valid final MailTLSSendList mailTLSSendList){
        return new ResponseEntity<Boolean>((Boolean)mailService.sendMailNewPass(mailTLSSendList).orElse(Boolean.valueOf(false)),HttpStatus.OK);
    }

}
