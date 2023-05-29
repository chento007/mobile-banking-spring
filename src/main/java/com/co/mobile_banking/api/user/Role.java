package com.co.mobile_banking.api.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;
    private Set<Authority> authorities;
    @Override
    public String getAuthority() {
        return "ROLE_"+name;
    }
}
