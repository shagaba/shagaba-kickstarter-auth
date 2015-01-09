package com.shagaba.kickstarter.auth.core.common.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccountDetails;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
      
        Object principal = authentication.getPrincipal();
        if (principal != null && principal instanceof UserAccountDetails) {
            return ((UserAccountDetails) principal).getUsername();
        }
        return null;
    }

}
