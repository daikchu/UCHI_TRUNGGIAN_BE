package com.vn.osp.notarialservices.preventinfoservice.domain;

import com.vn.osp.notarialservices.common.domain.AbstractAuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 12/6/2017.
 */
@Data
@Entity
@Table(name = "prevent_info_services")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name="findPreventInfoServiceByFilter",
                procedureName = "osp_npo_prevent_info_service_select_filter",
                resultClasses = {PreventInfoServiceBo.class},
                parameters = {
                        @StoredProcedureParameter(name="stringFilter",type = String.class,mode=ParameterMode.IN)
                }
        )
})
public class PreventInfoServiceBo extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "host")
    private String host;

    @Column(name = "post")
    private String post;


    @Column(name = "servicename")
    private String servicename;

    @Column(name = "method")
    private String method;

    @Column(name = "provincecode")
    private String provincecode;

    public PreventInfoServiceBo() {
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
