package com.vn.osp.notarialservices.preventinfoservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.common.dto.BaseEntityBeans;

/**
 * Created by Admin on 12/6/2017.
 */
@XStreamAlias("PreventInfoService")
public class PreventInfoService extends BaseEntityBeans{
    private Long id;
    private String host;
    private String post;
    private String servicename;
    private String method;
    private String provincecode;
    @JsonCreator
    public PreventInfoService(
            @JsonProperty(value="id") final Long id,
            @JsonProperty(value="host") final String host,
            @JsonProperty(value="post") final String post,
            @JsonProperty(value="servicename") final String servicename,
            @JsonProperty(value="method") final String method,
            @JsonProperty(value="provincecode") final String provincecode
    ){
        this.id=id;
        this.host = host;
        this.post = post;
        this.servicename= servicename;
        this.method = method;
        this.provincecode = provincecode;
    }

    public PreventInfoService() {
    }


    public Long getPid() {
        return id;
    }

    public void setPid(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }
}
