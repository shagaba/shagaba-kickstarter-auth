package com.shagaba.kickstarter.auth.core.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shagaba.kickstarter.auth.common.domain.message.Message;
import com.shagaba.kickstarter.auth.common.domain.message.MessageType;
import com.shagaba.kickstarter.auth.common.domain.message.SimpleMessage;
import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;
import com.shagaba.kickstarter.auth.core.repository.security.account.UserAccountRepository;
import com.shagaba.kickstarter.auth.core.service.security.account.authentication.AccountAuthenticationStatusManager;

@Component
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    public static final String FORM_USERNAME_KEY = "username";
    private String usernameParameter = FORM_USERNAME_KEY; 

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected AccountAuthenticationStatusManager accountAuthenticationStatusManager;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            String username = request.getParameter(usernameParameter);
    
            try {
                WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
                UserAccount userAccount = userAccountRepository.getUserAccountByUsername(username);
                accountAuthenticationStatusManager.updateLoginAuthenticationFailure(userAccount.getAccountId(), webAuthenticationDetails);
            } catch (Exception e) {
            }
        }
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        Message message = new SimpleMessage(MessageType.ERROR, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication failed.");
        objectMapper.writeValue(response.getWriter(), message);
    }

    /**
     * @return the usernameParameter
     */
    public String getUsernameParameter() {
        return usernameParameter;
    }

    /**
     * @param usernameParameter the usernameParameter to set
     */
    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

}
