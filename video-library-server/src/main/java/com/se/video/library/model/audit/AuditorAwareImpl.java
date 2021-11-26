package com.se.video.library.model.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


import com.se.video.library.security.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;




public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return Optional.ofNullable(userPrincipal.getName());
    }
}