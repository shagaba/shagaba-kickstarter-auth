package com.shagaba.kickstarter.auth.core.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shagaba.kickstarter.auth.common.domain.message.Message;
import com.shagaba.kickstarter.auth.common.domain.message.MessageType;
import com.shagaba.kickstarter.auth.common.domain.message.SimpleMessage;
import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.auth.core.service.security.account.authentication.AccountAuthenticationStatusManager;

@Component
public class LoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    protected AccountAuthenticationStatusManager accountAuthenticationStatusManager;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        UserAccountDetails userAccountDetails = (UserAccountDetails) authentication.getPrincipal();
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusManager.updateLoginAuthenticationSuccess(userAccountDetails, webAuthenticationDetails);
        response.addHeader("X-Auth-Token", accountAuthenticationStatus.getAccountToken().getSignature());
        
        response.setStatus(HttpServletResponse.SC_OK);
        clearAuthenticationAttributes(request);
        
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = new SimpleMessage(MessageType.INFO, HttpServletResponse.SC_OK, "Login Success");
        objectMapper.writeValue(response.getWriter(), message);

    }

}
