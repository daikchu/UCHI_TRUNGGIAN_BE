package com.vn.osp.notarialservices.function.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_function")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "osp_npo_function_by_code",
                procedureName = "osp_npo_function_by_code",
                resultClasses = { FunctionBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_function_by_type",
                procedureName = "osp_npo_function_by_type",
                resultClasses = { FunctionBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
})
public class FunctionBO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code", nullable = false)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent_code")
    private String parent_code;
    @Column(name = "type")
    private Long type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
