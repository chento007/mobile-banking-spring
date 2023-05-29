package com.co.mobile_banking.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@AllArgsConstructor
public class JwtFailureEvents {
    private final HttpServletResponse response;
    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent badCredentials) throws IOException {
        if (badCredentials.getAuthentication() instanceof BearerTokenAuthenticationToken) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> body=new HashMap<>();
            body.put("status",false);
            body.put("message",badCredentials.getException().getMessage());
            body.put("code",HttpStatus.UNAUTHORIZED.value());
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(),body);
        }
    }
}
