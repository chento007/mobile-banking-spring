package com.co.mobile_banking.api.user.web;

import jakarta.validation.constraints.NotNull;

public record IsDeletedDto(
        @NotNull
        Boolean status) {
}
