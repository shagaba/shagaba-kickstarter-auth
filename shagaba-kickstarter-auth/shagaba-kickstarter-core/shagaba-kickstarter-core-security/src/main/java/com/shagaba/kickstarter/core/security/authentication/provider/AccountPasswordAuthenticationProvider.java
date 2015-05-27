package com.shagaba.kickstarter.core.security.authentication.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.core.security.account.UserAccountDetailsManager;
import com.shagaba.kickstarter.core.security.account.authentication.AccountPasswordAuthenticationManager;

@Component
public class AccountPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountPasswordAuthenticationManager accountPasswordAuthenticationManager;

    @Autowired
    protected UserAccountDetailsManager userAccountDetailsManager;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        UsernamePasswordAuthenticationToken inPasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        if (!(inPasswordAuthenticationToken.getCredentials() instanceof String)) {
            return null;
        }

        String username = inPasswordAuthenticationToken.getName();
        String password = (String) inPasswordAuthenticationToken.getCredentials();
        
        UserAccountDetails userAccountDetails = (UserAccountDetails) userAccountDetailsManager.loadUserByUsername(username);
        if (userAccountDetails == null) {
            throw new UsernameNotFoundException(String.format("User Name %s Not Valid", username));
        }

        if (!accountPasswordAuthenticationManager.validate(password, userAccountDetails)) {
            throw new BadCredentialsException("Secured Password Not Valid");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAccountDetails, null, userAccountDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(authentication.getDetails());
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
