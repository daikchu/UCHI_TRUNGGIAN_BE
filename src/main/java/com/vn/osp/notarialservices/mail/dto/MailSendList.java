package com.vn.osp.notarialservices.mail.dto;

/**
 * Created by Admin on 6/1/2018.
 */
public class MailSendList {
    private String [] to;
    private String content;
    private String title;

    public MailSendList() {
    }

    public MailSendList(String[] to, String content, String title) {
        this.to = to;
        this.content = content;
        this.title = title;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

