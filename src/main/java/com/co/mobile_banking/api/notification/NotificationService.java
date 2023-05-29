package com.co.mobile_banking.api.notification;

import com.co.mobile_banking.api.notification.web.CreateNotificationDto;
public interface NotificationService {
    boolean pushNotification(CreateNotificationDto createNotificationDto);
}
