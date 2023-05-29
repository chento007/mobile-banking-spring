package com.co.mobile_banking.api.notification.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 *  - JsonProperty : if use want to tell spring that field use in bodyJson is included_segments
 *  but use in code normal is includedSegments
 * @param includedSegments
 * @param contents
 * @param appId
 */
@Builder
public record NotificationDto(
        @JsonProperty("included_segments")
        String[] includedSegments,
        ContentDto contents,
        @JsonProperty("app_id")
        String appId
) {}
