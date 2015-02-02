package com.shagaba.kickstarter.auth.core.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountToken;
import com.shagaba.kickstarter.auth.core.service.security.account.authentication.AccountTokenAuthenticationManager;

public class TokenAuthenticationFilter extends AuthenticationFilter {

    public static final String HEADER_TOKEN_KEY = "X-Auth-Token";
    private String tokenKey = HEADER_TOKEN_KEY;

    private AccountTokenAuthenticationManager accountTokenAuthenticationManager;

    @Override
    protected Authentication generateAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String signature = request.getHeader(tokenKey);
        if (signature == null) {
            return null;
        }
        String username = this.accountTokenAuthenticationManager.getUsername(signature);
        if (username == null) {
            username = "";
        }

        AccountToken accountToken = new AccountToken(username, signature, null, null);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, accountToken);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return authRequest;
    }

    /**
     * @param request
     * @param response
     * @param filterChain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, filterChain, authResult);
        filterChain.doFilter(request, response);
    }

    /**
     * @return the accountTokenAuthenticationManager
     */
    public AccountTokenAuthenticationManager getAccountTokenAuthenticationManager() {
        return accountTokenAuthenticationManager;
    }

    /**
     * @param accountTokenAuthenticationManager the accountTokenAuthenticationManager to set
     */
    public void setAccountTokenAuthenticationManager(AccountTokenAuthenticationManager accountTokenAuthenticationManager) {
        this.accountTokenAuthenticationManager = accountTokenAuthenticationManager;
    }

    /**
     * @return the tokenKey
     */
    public String getTokenKey() {
        return tokenKey;
    }

    /**
     * @param tokenKey the tokenKey to set
     */
    public void setTokenKey(String tokenKey) {
        Assert.hasText(tokenKey, "Token Key must not be empty or null");
        this.tokenKey = tokenKey;
    }

}