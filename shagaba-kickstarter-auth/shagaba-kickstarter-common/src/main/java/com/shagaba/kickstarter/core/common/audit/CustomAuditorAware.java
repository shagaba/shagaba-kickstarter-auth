package com.shagaba.kickstarter.core.common.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
      
        Object principal = authentication.getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

}
