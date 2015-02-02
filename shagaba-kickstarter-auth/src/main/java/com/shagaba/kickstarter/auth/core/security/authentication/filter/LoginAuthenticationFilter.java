package com.shagaba.kickstarter.auth.core.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

public class LoginAuthenticationFilter extends AuthenticationFilter {

    public static final String FORM_USERNAME_KEY = "username";
    public static final String FORM_PASSWORD_KEY = "password";

    private String usernameParameter = FORM_USERNAME_KEY;
    private String passwordParameter = FORM_PASSWORD_KEY;
    
    @Override
    protected Authentication generateAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = request.getParameter(usernameParameter);
        String password = request.getParameter(passwordParameter);
       if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return authRequest;
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

    /**
     * @return the passwordParameter
     */
    public String getPasswordParameter() {
        return passwordParameter;
    }

    /**
     * @param passwordParameter the passwordParameter to set
     */
    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

}