package com.vn.osp.notarialservices.contracttemplate.service;

import com.vn.osp.notarialservices.contracttemplate.dto.PropertyTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Created by TienManh on 8/5/2017.
 */
public interface PropertyTemplateService {
    Optional<List<PropertyTemplate>> listItem();
    Optional<List<PropertyTemplate>> listItemPage(String type, int offset, int number);
    Optional<List<PropertyTemplate>> getByType(String type);
    Optional<Long> countByType(String type);
    Optional<PropertyTemplate> getById(Integer id);
    Optional<Boolean> addItem(PropertyTemplate item);
    Optional<Boolean> updateItem(PropertyTemplate item);
    Boolean deleteItem(int id);
}
