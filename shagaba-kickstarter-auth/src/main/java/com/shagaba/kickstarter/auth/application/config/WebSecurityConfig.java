package com.shagaba.kickstarter.auth.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.shagaba.kickstarter.auth.core.security.authentication.filter.LoginAuthenticationFilter;
import com.shagaba.kickstarter.auth.core.security.authentication.filter.TokenAuthenticationFilter;
import com.shagaba.kickstarter.auth.core.security.authentication.handler.LoginAuthenticationFailureHandler;
import com.shagaba.kickstarter.auth.core.security.authentication.handler.LoginAuthenticationSuccessHandler;
import com.shagaba.kickstarter.auth.core.security.authentication.handler.TokenAuthenticationSuccessHandler;
import com.shagaba.kickstarter.auth.core.security.authentication.provider.AccountPasswordAuthenticationProvider;
import com.shagaba.kickstarter.auth.core.security.authentication.provider.AccountTokenAuthenticationProvider;
import com.shagaba.kickstarter.auth.core.service.security.account.AccountDetailsManager;
import com.shagaba.kickstarter.auth.core.service.security.account.authentication.AccountTokenAuthenticationManager;

@Configuration
@EnableWebMvcSecurity
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AccountDetailsManager accountDetailsManager;
    
    @Autowired
    protected AccountPasswordAuthenticationProvider accountPasswordAuthenticationProvider;
    
    @Autowired
    protected AccountTokenAuthenticationProvider accountTokenAuthenticationProvider;
    
    @Autowired
    protected LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    
    @Autowired
    protected LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;
    
    @Autowired
    protected TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler;
    
    @Autowired
    protected AccountTokenAuthenticationManager accountTokenAuthenticationManager;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(accountTokenAuthenticationProvider);
        auth.authenticationProvider(accountPasswordAuthenticationProvider);
        auth.userDetailsService(accountDetailsManager);
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
                .disable()
            .csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/authentication").permitAll()
                .anyRequest().permitAll()
                .and()
            .addFilterBefore(tokenAuthenticationFilter(), LogoutFilter.class)
            .addFilterAfter(loginAuthenticationFilter(), TokenAuthenticationFilter.class);

//                .and()
//            .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(restAuthenticationSuccessHandler())
//                .failureHandler(restAuthenticationFailureHandler())
//                .loginProcessingUrl("/login")
//                .and()
//            .logout()
//                .logoutUrl("/logout");
//                .and()
            
//            .addFilterBefore(principalTokenAuthenticationFilter(), LogoutFilter.class).
//                .and()
//            .exceptionHandling()
//                .accessDeniedHandler(restAccessDeniedHandler())
//                .authenticationEntryPoint(restAuthenticationEntryPoint());
//                .and()

    }
    
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        tokenAuthenticationFilter.setAccountTokenAuthenticationManager(accountTokenAuthenticationManager);
        tokenAuthenticationFilter.setAuthenticationSuccessHandler(tokenAuthenticationSuccessHandler);
        return tokenAuthenticationFilter;
    }
    
    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginAuthenticationFilter.setAuthenticationRequestMatcher(new AntPathRequestMatcher("/security/user/authentication", "POST"));
        loginAuthenticationFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
        return loginAuthenticationFilter;
    }
    
//    @Bean
//    public RestAccessDeniedHandler restAccessDeniedHandler() {
//        return new RestAccessDeniedHandler();
//    }
//    
//    @Bean
//    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }
    
//    @Bean
//    public RestAuthenticationFailureHandler restAuthenticationFailureHandler() {
//        return new RestAuthenticationFailureHandler();
//    }
    

}
