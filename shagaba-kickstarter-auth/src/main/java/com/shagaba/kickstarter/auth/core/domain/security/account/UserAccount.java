package com.shagaba.kickstarter.auth.core.domain.security.account;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shagaba.kickstarter.auth.core.common.audit.AuditingDomain;

@Document
public class UserAccount extends AuditingDomain {
    
    @Id
    protected String accountId;

    protected String username;

    protected String password;
    
    protected String emailAddress;
    
    protected List<ChallengeQuestion> challengeQuestions;

    protected String salt;

    protected List<UserAccountRole> roles;
    
    /**
     * 
     */
    public UserAccount() {
        super();
    }

    /**
     * @param accountId
     * @param username
     * @param password
     * @param emailAddress
     * @param challengeQuestions
     * @param salt
     * @param roles
     */
    public UserAccount(String accountId, String username, String password, String emailAddress, List<ChallengeQuestion> challengeQuestions, String salt, List<UserAccountRole> roles) {
        super();
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.challengeQuestions = challengeQuestions;
        this.salt = salt;
        this.roles = roles;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the roles
     */
    public List<UserAccountRole> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<UserAccountRole> roles) {
        this.roles = roles;
    }
    
    
 }
