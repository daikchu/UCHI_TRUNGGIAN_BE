package com.vn.osp.notarialservices.province.controller;

import com.vn.osp.notarialservices.common.hateos.ExtendedLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by ProBook on 5/25/2017.
 */
@Service
public class ProvinceControllerHateoasBuilder {
    private final ProvinceLinksFactory provinceLinksFactory;

    @Autowired
    public ProvinceControllerHateoasBuilder(final ProvinceLinksFactory provinceLinksFactory) {
        this.provinceLinksFactory = provinceLinksFactory;
    }

}
