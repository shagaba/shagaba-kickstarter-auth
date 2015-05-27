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
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountToken;
import com.shagaba.kickstarter.core.security.account.UserAccountDetailsManager;
import com.shagaba.kickstarter.core.security.account.authentication.AccountAuthenticationStatusManager;
import com.shagaba.kickstarter.core.security.account.authentication.AccountTokenAuthenticationManager;

@Component
public class AccountTokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountTokenAuthenticationManager accountTokenAuthenticationManager;
    
    @Autowired
    private AccountAuthenticationStatusManager accountAuthenticationStatusManager;

    @Autowired
    protected UserAccountDetailsManager userAccountDetailsManager;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        UsernamePasswordAuthenticationToken inPasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        if (!(inPasswordAuthenticationToken.getCredentials() instanceof AccountToken)){
            return null;
        }
        
        String username = inPasswordAuthenticationToken.getName();
        AccountToken accountToken = (AccountToken) inPasswordAuthenticationToken.getCredentials();
        
        UserAccountDetails userAccountDetails = (UserAccountDetails) userAccountDetailsManager.loadUserByUsername(username);
        if (userAccountDetails == null) {
            throw new UsernameNotFoundException(String.format("User Name %s Not Valid", username));
        }

        if (!accountTokenAuthenticationManager.validateToken(accountToken.getSignature(), userAccountDetails)) {
            throw new BadCredentialsException("Secured Token Not Valid");
        }
        
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusManager.getAccountAuthenticationStatusByAccountId(userAccountDetails.getAccountId());
//        if (!accountToken.getSignature().equals(accountAuthenticationStatus.getAccountToken().getSignature())) {
//            throw new BadCredentialsException("Secured Token Does Not Match");
//        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAccountDetails, null, userAccountDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(authentication.getDetails());
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
