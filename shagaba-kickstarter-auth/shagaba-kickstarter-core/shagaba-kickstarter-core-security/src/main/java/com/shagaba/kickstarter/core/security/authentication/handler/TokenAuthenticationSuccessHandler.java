package com.shagaba.kickstarter.core.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.core.security.account.authentication.AccountAuthenticationStatusManager;

@Component
public class TokenAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    protected AccountAuthenticationStatusManager accountAuthenticationStatusManager;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        UserAccountDetails userAccountDetails = (UserAccountDetails) authentication.getPrincipal();
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusManager.updateTokenAuthenticationSuccess(userAccountDetails, webAuthenticationDetails);
        response.addHeader("X-Auth-Token", accountAuthenticationStatus.getAccountToken().getSignature());

        response.setStatus(HttpServletResponse.SC_OK);
        clearAuthenticationAttributes(request);
    }

}
