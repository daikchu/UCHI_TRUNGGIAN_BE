package com.vn.osp.notarialservices.preventinfoservice.service;

import com.vn.osp.notarialservices.preventinfoservice.dto.PreventInfoService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 12/6/2017.
 */
public interface PreventInfoServiceService {
    Optional<List<PreventInfoService>> findPreventInfoServiceByFilter(String stringFilter);
}
