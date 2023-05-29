package com.co.mobile_banking.security;

import com.co.mobile_banking.api.auth.AuthMapper;
import com.co.mobile_banking.api.user.Authority;
import com.co.mobile_banking.api.user.Role;
import com.co.mobile_banking.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final AuthMapper authMapper;

    /**
     * UserDetails : represent the core user information required for auth
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public UserDetails loadUserByUsername(String username)throws AuthenticationException {
        User user=authMapper.loadUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                    "User is not valid"
                )
        );
        System.out.println("User : "+user);
        for(Role role:user.getRoles()){
            for(Authority authority:role.getAuthorities()){
                System.out.println(authority.getName());
            }
        }
        CustomUserDetails customUserDetails=new CustomUserDetails();
        customUserDetails.setUser(user);
        System.out.println("custom user Detail : "+customUserDetails);
        System.out.println("custom user Detail : "+customUserDetails.getAuthorities());
        return customUserDetails;
    }
}
