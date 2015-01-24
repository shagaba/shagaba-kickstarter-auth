package com.shagaba.kickstarter.auth.rest.domain.security.account;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


public class UserAccount{
    
    protected String id;

    @NotNull
    @Size(min=5, max=30)
    protected String username;

    @NotNull
    @Size(min=5, max=23)
    protected String password;

    @NotNull
    @Email
    protected String emailAddress;
    
    protected List<ChallengeQuestion> challengeQuestions;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

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

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the challengeQuestions
     */
    public List<ChallengeQuestion> getChallengeQuestions() {
        return challengeQuestions;
    }

    /**
     * @param challengeQuestions the challengeQuestions to set
     */
    public void setChallengeQuestions(List<ChallengeQuestion> challengeQuestions) {
        this.challengeQuestions = challengeQuestions;
    }

 }
