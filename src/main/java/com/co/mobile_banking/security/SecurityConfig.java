package com.co.mobile_banking.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

/**
 * SecurityConfig : config security such as :
 * - set role for user,admin
 * - set permission api for those who can use
 * EnableWebSecurity : use to enable web security and configure Spring Security
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    /**
     * - define role user
     * InMemory because we want to store data in local when it starts until the program shut down
     * - UserDetailsService : is an interface use to load user specific data , such as username,password
     * role, and you also can use to load user information from a database
     * - InMemoryUserDetailManager : use to store user information in memory . it uses full for testing
     * when database not require by using User class , which represent user detail
     */
    private final PasswordEncoder encoder;

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * DaoAuthenticationProvider : use to validate authentication and password
     *
     * @return
     * @throws AuthenticationException
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws AuthenticationException {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsServiceImp);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

    /**
     * - SecurityFilterChain : is the primary filter that spring security use to secure a web app.
     * it chains of filter that process incoming HTTP request and applies security check .
     * it means : when http request to sever want to set permission for those request and who can handle that request
     * - HttpSecurity : use to define secure rule for specific http request .
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:read");
            auth.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:write");
            auth.requestMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:delete");
            auth.requestMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:update");
            auth.requestMatchers(HttpMethod.GET,"/api/v1/account-types/**").hasAnyAuthority("SCOPE_account:read");
            auth.anyRequest().authenticated();
        });
        // http.httpBasic();
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        return http.build();
    }

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwtSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwtSet);
    }
}
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);
//        return userDetailsManager;
//    }