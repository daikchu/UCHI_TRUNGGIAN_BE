package com.vn.osp.notarialservices.utils;

import com.sun.javafx.runtime.SystemProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Created by Admin on 6/1/2018.
 */
public class SendMailUtil {
    private static final Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

    public static void sendGmail(String[] to, String subject, String body) throws AddressException, MessagingException {
        String from = com.vn.osp.notarialservices.utils.SystemProperties.getProperty("email.username");
        String pass = com.vn.osp.notarialservices.utils.SystemProperties.getProperty("email.password");
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        // To get the array of addresses
        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
        message.setContent(body, "text/html; charset=UTF-8");
        message.setSubject(subject, "UTF-8");
        // message.setText(body, "UTF-8", "text/html");

        // //System.out.println(message.getContent().toString());
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

//    public static void main(String[] args) {
//        System.out.println("backup_%date:~7,2%-%date:~4,2%-%date:~10,4%.sql");
//    }
}
