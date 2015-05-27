package com.shagaba.kickstarter.core.security.authentication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountLogin;

public class LoginAuthenticationFilter extends AuthenticationFilter {

	@Override
	protected Authentication generateAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		AccountLogin accountLogin = new ObjectMapper().readValue(request.getInputStream(), AccountLogin.class);
		if (accountLogin == null || Strings.isNullOrEmpty(accountLogin.getUsername()) || Strings.isNullOrEmpty(accountLogin.getPassword())) {
			return null;
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(accountLogin.getUsername(),
				accountLogin.getPassword());
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		return authRequest;
	}

}