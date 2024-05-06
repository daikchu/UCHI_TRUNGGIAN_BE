package com.vn.osp.notarialservices.citizenVerificationNumber.domain;

import java.io.Serializable;
import java.util.Objects;

public class CitizenVerifyNumberId implements Serializable {
    private String notary_office_code;
    private String province_code;

    public CitizenVerifyNumberId() {
    }

    // Constructors, getters, and setters

    public CitizenVerifyNumberId(String notary_office_code, String province_code) {
        this.notary_office_code = notary_office_code;
        this.province_code = province_code;
    }

    // Implement equals() and hashCode() methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitizenVerifyNumberId that = (CitizenVerifyNumberId) o;

        if (!notary_office_code.equals(that.notary_office_code)) return false;
        return province_code.equals(that.province_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notary_office_code, province_code);
    }
}
