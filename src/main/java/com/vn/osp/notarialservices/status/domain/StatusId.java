package com.vn.osp.notarialservices.status.domain;

import java.io.Serializable;

public class StatusId implements Serializable {
    private String code;
    private String type;

    public StatusId() {
    }

    // Constructors, getters, and setters

    public StatusId(String code, String type) {
        this.code = code;
        this.type = type;
    }

    // Implement equals() and hashCode() methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusId that = (StatusId) o;

        if (!code.equals(that.code)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
