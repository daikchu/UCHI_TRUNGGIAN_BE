package com.vn.osp.notarialservices.common.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by longtran on 19/10/2016.
 */
@EqualsAndHashCode(of = {"createdTs", "updatedTs"})
@ToString(of = {"createdTs", "updatedTs"})
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 6384069660089559035L;

    @Column(name = "created_ts", nullable = false)
    private LocalDate createdTs;

    @Column(name = "updated_ts", nullable = false)
    private LocalDate updatedTs;

    @PrePersist
    protected void onCreate() {
        updatedTs = createdTs = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTs = LocalDate.now();
    }

    public LocalDate getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(LocalDate createdTs) {
        this.createdTs = createdTs;
    }

    public LocalDate getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(LocalDate updatedTs) {
        this.updatedTs = updatedTs;
    }
}
