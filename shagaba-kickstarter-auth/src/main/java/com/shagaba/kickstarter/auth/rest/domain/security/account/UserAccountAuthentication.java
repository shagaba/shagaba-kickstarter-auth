package com.shagaba.kickstarter.auth.rest.domain.security.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserAccountAuthentication{
    
    @NotNull
    @Size(min=5, max=30)
    protected String username;

    @NotNull
    @Size(min=5, max=23)
    protected String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
 }
