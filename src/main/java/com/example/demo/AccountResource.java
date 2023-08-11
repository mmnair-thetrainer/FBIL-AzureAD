package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource {

    @GetMapping("/account")
    public Authentication getAccount() {
        System.out.println("In account/");
        return SecurityContextHolder.getContext().getAuthentication();

    }

    @GetMapping("/auth-user")
    public String user() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("In auth-user/");
        return username;
    }

    @GetMapping("/subject")
    public String subject(){
        System.out.println("In subject/");

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/token")
    public String user(OAuth2AuthenticationToken authentication) {
        System.out.println("In token/");
        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
        return user.getIdToken().getTokenValue();
    }
}
