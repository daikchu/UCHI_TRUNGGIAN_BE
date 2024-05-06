package com.vn.osp.notarialservices.preventinfoservice.repository;

import com.vn.osp.notarialservices.preventinfoservice.domain.PreventInfoServiceBo;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 12/6/2017.
 */
public interface PreventInfoServiceRepositoryCustom {
    /**
     * Method actually triggering a finder but being overridden.
     */
    void findByOverridingMethod();



    Optional<List<PreventInfoServiceBo>> findPreventInfoServiceByFilter(String stringFilter);

}
