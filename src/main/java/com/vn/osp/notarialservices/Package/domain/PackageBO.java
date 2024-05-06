package com.vn.osp.notarialservices.Package.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_package")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "osp_npo_package_delete_by_id",
                procedureName = "osp_npo_package_delete_by_id",
                parameters = {
                        @StoredProcedureParameter(name = "id", type = Long.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_package_count_total",
                procedureName = "osp_npo_package_count_total",
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "osp_npo_package_select_by_filter",
                procedureName = "osp_npo_package_select_by_filter",
                resultClasses = { PackageBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN),
                }
        ),
})
public class PackageBO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "user_num")
    private Long user_num;
    @Column(name = "description")
    private String description;
    @Column(name = "note")
    private String note;
}
