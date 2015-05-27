package com.shagaba.kickstarter.core.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class AuthenticationFilter extends OncePerRequestFilter {

    protected AuthenticationManager authenticationManager = null;
    protected AuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    protected AuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    protected RememberMeServices rememberMeServices = new NullRememberMeServices();

	protected RequestMatcher authenticationRequestMatcher = null;
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    protected abstract Authentication generateAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (authenticationRequestMatcher != null && !authenticationRequestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authRequest = generateAuthenticationRequest(request, response, filterChain);
        if (authRequest == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authResult = this.getAuthenticationManager().authenticate(authRequest);
            if (authResult != null && authResult.isAuthenticated()) {
                successfulAuthentication(request, response, filterChain, authResult);
            }
        } catch (InternalAuthenticationServiceException failed) {
            logger.error("An internal error occurred while trying to authenticate the user.", failed);
            unsuccessfulAuthentication(request, response, failed);
        } catch (AuthenticationException failed) {
            unsuccessfulAuthentication(request, response, failed);
        }
    }

    /**
     * @param request
     * @param response
     * @param filterChain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        rememberMeServices.loginSuccess(request, response, authResult);
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        rememberMeServices.loginFail(request, response);
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * @return the authenticationManager
     */
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * @param authenticationManager
     *            the authenticationManager to set
     */
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * @return the authenticationRequestMatcher
     */
    public RequestMatcher getAuthenticationRequestMatcher() {
        return authenticationRequestMatcher;
    }

    /**
     * @param authenticationRequestMatcher
     *            the authenticationRequestMatcher to set
     */
    public void setAuthenticationRequestMatcher(RequestMatcher authenticationRequestMatcher) {
        Assert.notNull(authenticationRequestMatcher, "authenticationRequestMatcher cannot be null");
        this.authenticationRequestMatcher = authenticationRequestMatcher;
    }

    /**
     * @return the authenticationDetailsSource
     */
    public AuthenticationDetailsSource<HttpServletRequest, ?> getAuthenticationDetailsSource() {
        return authenticationDetailsSource;
    }

    /**
     * @param authenticationDetailsSource
     *            the authenticationDetailsSource to set
     */
    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "authenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    /**
     * @return the authenticationSuccessHandler
     */
    public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return authenticationSuccessHandler;
    }

    /**
     * @param authenticationSuccessHandler
     *            the authenticationSuccessHandler to set
     */
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        Assert.notNull(authenticationSuccessHandler, "authenticationSuccessHandler cannot be null");
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    /**
     * @return the authenticationFailureHandler
     */
    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    /**
     * @param authenticationFailureHandler
     *            the authenticationFailureHandler to set
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        Assert.notNull(authenticationFailureHandler, "authenticationFailureHandler cannot be null");
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
    
    /**
	 * @return the rememberMeServices
	 */
	public RememberMeServices getRememberMeServices() {
		return rememberMeServices;
	}

    /**
     * @param rememberMeServices
     *            the rememberMeServices to set
     */
    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        Assert.notNull(rememberMeServices, "rememberMeServices cannot be null");
        this.rememberMeServices = rememberMeServices;
    }

}