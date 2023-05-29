package com.co.mobile_banking.api.user.web;

import lombok.Builder;

@Builder
public record UpdateUserDto(String name, String gender) {
}
