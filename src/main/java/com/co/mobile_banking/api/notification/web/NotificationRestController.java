package com.co.mobile_banking.api.notification.web;

import com.co.mobile_banking.api.notification.NotificationServiceImp;
import com.co.mobile_banking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationRestController {
    private final NotificationServiceImp notificationServiceImp;
    @PostMapping
    BaseRest<?> pushNotification(@RequestBody CreateNotificationDto createNotificationDto){
        boolean b = notificationServiceImp.pushNotification(createNotificationDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Notification has been push")
                .timestamp(LocalDateTime.now())
                .data(createNotificationDto.contents())
                .build();
    }
}
