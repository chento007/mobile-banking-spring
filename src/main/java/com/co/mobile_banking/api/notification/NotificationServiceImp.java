package com.co.mobile_banking.api.notification;

import com.co.mobile_banking.api.notification.web.CreateNotificationDto;
import com.co.mobile_banking.api.notification.web.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;


@Service
public class NotificationServiceImp implements NotificationService{
    private RestTemplate restTemplate;
    @Value("${oneSignal.api-key}")
    private String restApiKey;
    @Value("${oneSignal.app-id}")
    private String appId;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean pushNotification(CreateNotificationDto createNotificationDto) {
        // syntax to set header
        HttpHeaders headers=new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "Basic "+restApiKey);
        headers.set("content-type", "application/json");
        NotificationDto body=NotificationDto.builder()
                .includedSegments(createNotificationDto.includedSegments())
                .contents(createNotificationDto.contents())
                .appId(appId)
                .build();
        HttpEntity<NotificationDto> requestBody=new HttpEntity<>(body,headers);
        ResponseEntity<?> response=restTemplate.postForEntity(
                "https://onesignal.com/api/v1/notifications",
                requestBody,
                Map.class
        );
        return response.getStatusCode() == HttpStatus.OK;
    }
}
