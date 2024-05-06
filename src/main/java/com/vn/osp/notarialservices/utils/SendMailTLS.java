package com.vn.osp.notarialservices.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Admin on 8/1/2018.
 */
public class SendMailTLS {
    public static boolean mail(String mailTo, String newPass, String account, String linkLogin) {

        final String username = SystemProperties.getProperty("email.username"); //"ospuchitest@gmail.com";
        final String password = SystemProperties.getProperty("email.password");//"tongcongty";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject(SystemProperties.getProperty("v3_release_pass"), "utf-8");
            message.setContent("<h4 style='color:black'>Mật khẩu mới cho tài khoản " + account + " là : <a style='text-decoration:none;' href='#'>" + newPass + "</a></h4>" +
                    "<p>Để bảo vệ thông tin cá nhân hãy đăng nhập và đổi mật khẩu " + "<a style='color:blue;text-decoration:none;' href='" + linkLogin + "'>Tại đây</a>" + " </p><br>" +
                    "<div style='with:100%;text-align:center;'>" +
                    "<p style='text-align:center;font-weight:bold'>CÔNG TY CỔ PHẦN CÔNG NGHỆ PHẦN MỀM VÀ NỘI DUNG SỐ OSP</p>" +
                    "<p style='text-align:center;'>Trụ sở:  Tầng 7, Toà Nhà Đại Phát, số 82, Phố Duy Tân, Cầu Giấy , Hà Nội</p>" +
                    "<p style='text-align:center;'>Tel: (024) 3568 2502; (024) 3568 2503; Fax: (024) 3568 2504</p>" +
                    "<p style='text-align:center;'>Website: www.osp.com.vn</p></div>", "text/html; charset=UTF-8");
            //message.setText(newPass);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
