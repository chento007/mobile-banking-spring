package com.co.mobile_banking.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
public record AccountTypeDto(
        @NotBlank(message = "name is require")
        String name) {
}
