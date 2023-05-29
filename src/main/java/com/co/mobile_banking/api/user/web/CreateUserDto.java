package com.co.mobile_banking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record CreateUserDto(
        @NotBlank(message = "name is require") String name,
        @NotBlank(message = "gender is gender") String gender,
        String oneSignalId,
        String studentCardId,
        @NotNull Boolean isStudent) {
}
