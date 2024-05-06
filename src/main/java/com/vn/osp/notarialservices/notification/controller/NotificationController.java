package com.vn.osp.notarialservices.notification.controller;


import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.notification.domain.Notifications;
import com.vn.osp.notarialservices.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<PagingResult> page(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "numberPerPage", required = true, defaultValue = "20") int numberPerPage,
            @RequestParam(name = "type", required = false) String type)
    {
        PagingResult pageResult = notificationService.page(page, numberPerPage, type, null);
        return ResponseEntity.ok(pageResult);
    }
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Long> count(
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "view_status", required = false) Integer view_status)
    {
        Long count = notificationService.count(type, view_status);
        return ResponseEntity.ok(count);
    }

    @RequestMapping(value = "/view-status", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@RequestBody final Notifications item){
        notificationService.updateViewStatus(item.getId(), item.getView_status());
        return ResponseEntity.ok(true);
    }
}
