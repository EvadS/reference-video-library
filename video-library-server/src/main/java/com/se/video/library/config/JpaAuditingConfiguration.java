package com.se.video.library.config;


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

import javax.annotation.PostConstruct;
import java.util.Optional;

//@EnableJpaAuditing

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories
//@EnableJpaAuditing
public class JpaAuditingConfiguration {
//    @Bean
//    public AuditorAware<Long> auditorProvider() {
//        return new SpringSecurityAuditAwareImpl();
//    }
//}
//
//class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {
//
//    @Override
//    public Optional<Long> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null ||
//                !authentication.isAuthenticated() ||
//                authentication instanceof AnonymousAuthenticationToken) {
//            return Optional.empty();
//        }
//
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        return Optional.ofNullable(userPrincipal.getId());
//    }
//
//    @PostConstruct
//    private void init(){
//        System.out.println("here------------------------=========");
//    }
}