//package com.vn.osp.notarialservices.packagefunction.domain;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "npo_package_function")
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(
//                name = "osp_npo_package_function_insert",
//                procedureName = "osp_npo_package_function_insert",
//                parameters = {
//                        @StoredProcedureParameter(name = "package_code", type = String.class, mode = ParameterMode.IN),
//                        @StoredProcedureParameter(name = "function_code", type = String.class, mode = ParameterMode.IN)
//                }
//        ),
////        @NamedStoredProcedureQuery(
////                name = "osp_npo_package_code_delete_by_id",
////                procedureName = "osp_npo_package_code_delete_by_id",
////                parameters = {
////                        @StoredProcedureParameter(name = "code", type = Long.class, mode = ParameterMode.IN),
////                }
////        ),
//})
//public class PackageFunctionBO {
//    private static final long serialVersionUID = 1L;
//    @Column(name = "package_code")
//    private String package_code;
//
//    @Column(name = "function_code")
//    private String function_code;
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public String getPackage_code() {
//        return package_code;
//    }
//
//    public void setPackage_code(String package_code) {
//        this.package_code = package_code;
//    }
//
//    public String getFunction_code() {
//        return function_code;
//    }
//
//    public void setFunction_code(String function_code) {
//        this.function_code = function_code;
//    }
//
//}

package com.vn.osp.notarialservices.packagefunction.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_package_function")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "osp_npo_package_function_insert",
                procedureName = "osp_npo_package_function_insert",
                parameters = {
                        @StoredProcedureParameter(name = "package_code", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "function_code", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_package_function_delete_by_id",
                procedureName = "osp_npo_package_function_delete_by_id",
                parameters = {
                        @StoredProcedureParameter(name = "id", type = Long.class, mode = ParameterMode.IN),
                }
        ),
})
public class PackageFunctionBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name = "package_code")
    private String package_code;

    @Column(name = "function_code")
    private String function_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackage_code() {
        return package_code;
    }

    public void setPackage_code(String package_code) {
        this.package_code = package_code;
    }

    public String getFunction_code() {
        return function_code;
    }

    public void setFunction_code(String function_code) {
        this.function_code = function_code;
    }


}

