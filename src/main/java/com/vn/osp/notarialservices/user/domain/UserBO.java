package com.vn.osp.notarialservices.user.domain;

import com.vn.osp.notarialservices.common.domain.AbstractAuditEntity;
import com.vn.osp.notarialservices.user.dto.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by longtran on 20/10/2016.
 */
@Data
@Entity
@Table(name = "npo_user")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "stp_npo_user_select_by_filter",
                procedureName = "stp_npo_user_select_by_filter",
                resultClasses = { UserBO.class },
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "stp_npo_user_add",
                procedureName = "stp_npo_user_add",
                parameters = {
                        @StoredProcedureParameter(name = "xml_content", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "stp_npo_user_update",
                procedureName = "stp_npo_user_update",
                parameters = {
                        @StoredProcedureParameter(name = "xml_content", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "stp_npo_user_delete_by_id",
                procedureName = "stp_npo_user_delete_by_id",
                parameters = {
                        @StoredProcedureParameter(name = "id", type = Long.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "stp_npo_user_count_total_by_filter",
                procedureName = "stp_npo_user_count_total_by_filter",
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN)
                }
        ),
})
public class UserBO extends AbstractAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="office_id")
    private Long office_id;

    @Column(name="family_name")
    private String family_name;

    @Column(name="first_name")
    private String first_name;

    @Column(name="account")
    private String account;

    @Column(name="password")
    private String password;

    @Column(name="sex")
    private Long sex;

    @Column(name="active_flg")
    private Long active_flg;

    @Column(name="hidden_flg")
    private Long hidden_flg;

    @Column(name="role")
    private String role;

    @Column(name="birthday")
    private String birthday;

    @Column(name="telephone")
    private String telephone;

    @Column(name="mobile")
    private String mobile;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="last_login_date")
    private java.sql.Timestamp last_login_date;

    @Column(name="entry_user_id")
    private Long entry_user_id;

    @Column(name="entry_user_name")
    private String entry_user_name;

    @Column(name="entry_date_time")
    private java.sql.Timestamp entry_date_time;

    @Column(name="update_user_id")
    private Long update_user_id;

    @Column(name="update_user_name")
    private String update_user_name;

    @Column(name="update_date_time")
    private java.sql.Timestamp update_date_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Long office_id) {
        this.office_id = office_id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getHidden_flg() {
        return hidden_flg;
    }

    public void setHidden_flg(Long hidden_flg) {
        this.hidden_flg = hidden_flg;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Timestamp last_login_date) {
        this.last_login_date = last_login_date;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }

    public Timestamp getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(Timestamp entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }
}
