package com.vn.osp.notarialservices.province.controller;

import com.vn.osp.notarialservices.common.hateos.ExtendedLink;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by ProBook on 5/25/2017.
 */
@Service
public class ProvinceLinksFactory {
    private static final ProvinceController CONTROLLER = methodOn(ProvinceController.class);
    public ExtendedLink getFindProvinceByFilter(final String stringFilter, final String rel) {
        final Link link = linkTo(CONTROLLER.findProvinceByFilter(stringFilter)).withRel(rel);

        return ExtendedLink.extend(link)
                .withName("findProvinceByFilter")
                .withMethods("POST")
                .withDescription("Filter Announcement");
    }
}
