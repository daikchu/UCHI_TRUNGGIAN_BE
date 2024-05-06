package com.vn.osp.notarialservices.contracttemplate.service;

import com.vn.osp.notarialservices.contracttemplate.domain.PropertyTemplateBO;
import com.vn.osp.notarialservices.contracttemplate.dto.PropertyTemplate;
//import com.vn.osp.notarialservices.contracttemplate.repository.PrivyPropertyTemplateRepository;
import com.vn.osp.notarialservices.contracttemplate.repository.PropertyTemplateRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by TienManh on 8/5/2017.
 */
@Component
public class PropertyTemplateServiceImpl implements PropertyTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyTemplateServiceImpl.class);
    private final PropertyTemplateRepositoryCustom repository;
    private final PropertyTemplateConverter converter;

    @Autowired
    public PropertyTemplateServiceImpl(final PropertyTemplateRepositoryCustom repository, final PropertyTemplateConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<List<PropertyTemplate>> listItem() {
        List<PropertyTemplateBO> list=repository.list().orElse(new ArrayList<>());
        List<PropertyTemplate> lst=new ArrayList<>();
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                lst.add(Optional.ofNullable(list.get(i)).map(converter::convert).orElse(new PropertyTemplate()));
            }
        }
        return Optional.ofNullable(lst);
    }

    @Override
    public Optional<List<PropertyTemplate>> listItemPage(String type, int offset, int number) {
        List<PropertyTemplateBO> list=repository.listItemPageByType(type,offset,number).orElse(new ArrayList<>());
        List<PropertyTemplate> lst=new ArrayList<>();
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                lst.add(Optional.ofNullable(list.get(i)).map(converter::convert).orElse(new PropertyTemplate()));
            }
        }
        return Optional.ofNullable(lst);
    }

    @Override
    public Optional<List<PropertyTemplate>> getByType(String type) {
        List<PropertyTemplateBO> list=repository.getByType(type).orElse(new ArrayList<>());
        List<PropertyTemplate> lst=new ArrayList<>();
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                lst.add(Optional.ofNullable(list.get(i)).map(converter::convert).orElse(new PropertyTemplate()));
            }
        }
        return Optional.ofNullable(lst);
    }

    @Override
    public Optional<Long> countByType(String type) {
        return repository.countByType(type);
    }

    @Override
    public Optional<PropertyTemplate> getById(Integer id) {
        PropertyTemplateBO itemBO=repository.getById(id).orElse(new PropertyTemplateBO());
        return Optional.ofNullable(itemBO).map(converter::convert);
    }

    @Override
    public Optional<Boolean> addItem(PropertyTemplate item) {
        PropertyTemplateBO itemAdd=converter.convert(item);
        return repository.add(itemAdd);
    }

    @Override
    public Optional<Boolean> updateItem(PropertyTemplate item) {
        PropertyTemplateBO itemUpdate=converter.convert(item);
        return repository.update(itemUpdate);
    }

    @Override
    public Boolean deleteItem(int id) {
        return repository.deleteItem(id);
    }
}
