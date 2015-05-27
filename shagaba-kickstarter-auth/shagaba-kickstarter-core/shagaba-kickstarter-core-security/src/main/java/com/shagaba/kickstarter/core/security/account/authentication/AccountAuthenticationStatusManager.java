package com.shagaba.kickstarter.core.security.account.authentication;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.shagaba.kickstarter.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountToken;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AuthenticationAttempt;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AuthenticationAttemptStatus;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AuthenticationTraking;
import com.shagaba.kickstarter.core.domain.security.account.authentication.LockoutType;
import com.shagaba.kickstarter.core.repository.security.account.authentication.AccountAuthenticationStatusRepository;

@PropertySource("classpath:userAccount.properties")
@Component
public class AccountAuthenticationStatusManager {
    
    @Value("${account.lockout.enable}")
    protected boolean isLockoutEnabled;
    
    @Value("${account.lockout.temporary.enable}")
    protected boolean isTemporaryLockoutEnabled;

    @Value("${account.authentication.history.samples}")
    protected int historySamples;
    
    @Value("${account.lockout.attempts.total}")
    protected int totalAttempts;
    
    @Value("${account.lockout.attempts}")
    protected int lockoutAttempts;
    
    @Value("${account.lockout.attempts.threshold}")
    protected int lockoutAttemptsThreshold;
    
    @Value("${account.lockout.duration}")
    protected int lockoutDuration;
    
    @Value("${account.lockout.duration.threshold}")
    protected int lockoutDurationThreshold;
    
    @Autowired
    protected AccountTokenAuthenticationManager accountTokenAuthenticationManager;

    @Autowired
    protected AccountAuthenticationStatusRepository accountAuthenticationStatusRepository;

    /**
     * @param id
     * @return
     */
    public AccountAuthenticationStatus getAccountAuthenticationStatusByAccountId(String accountId) {
        Assert.notNull(accountId);
        return accountAuthenticationStatusRepository.findOne(accountId);
    }
  
    /**
     * @param userAccountDetails
     * @param webAuthenticationDetails
     * @return
     */
    public AccountAuthenticationStatus updateLoginAuthenticationSuccess(UserAccountDetails userAccountDetails, WebAuthenticationDetails webAuthenticationDetails) {
        
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusRepository.findOne(userAccountDetails.getAccountId());
        if (accountAuthenticationStatus == null) {
            accountAuthenticationStatus = new AccountAuthenticationStatus();
            accountAuthenticationStatus.setAccountId(userAccountDetails.getAccountId());
        }

        AccountToken accountToken = accountTokenAuthenticationManager.generateToken(userAccountDetails);
        AuthenticationTraking logingAuthTraking = addAuthenticationAttempt(accountAuthenticationStatus.getLogingAuthTraking(), new AuthenticationAttempt(webAuthenticationDetails.getRemoteAddress(), accountToken.getTimestamp(), AuthenticationAttemptStatus.SUCCESSFUL));

        accountAuthenticationStatus.setAccountToken(accountToken);
        accountAuthenticationStatus.setLogingAuthTraking(logingAuthTraking);
        accountAuthenticationStatus.setLockoutExperation(null);
        accountAuthenticationStatus.setLockoutType(null);
        
        return accountAuthenticationStatusRepository.save(accountAuthenticationStatus);
    }

    /**
     * @param userAccountDetails
     * @param webAuthenticationDetails
     * @return
     */
    public AccountAuthenticationStatus updateTokenAuthenticationSuccess(UserAccountDetails userAccountDetails, WebAuthenticationDetails webAuthenticationDetails) {
        
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusRepository.findOne(userAccountDetails.getAccountId());

        AuthenticationTraking tokenAuthTraking = addAuthenticationAttempt(accountAuthenticationStatus.getTokenAuthTraking(), new AuthenticationAttempt(webAuthenticationDetails.getRemoteAddress(), new Date(), AuthenticationAttemptStatus.SUCCESSFUL));

        accountAuthenticationStatus.setTokenAuthTraking(tokenAuthTraking);
        
        return accountAuthenticationStatusRepository.save(accountAuthenticationStatus);
    }
    

    /**
     * @param id
     * @param webAuthenticationDetails
     * @return
     */
    public AccountAuthenticationStatus updateLoginAuthenticationFailure(String accountId, WebAuthenticationDetails webAuthenticationDetails) {
        
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusRepository.findOne(accountId);
        if (accountAuthenticationStatus == null) {
            accountAuthenticationStatus = new AccountAuthenticationStatus();
            accountAuthenticationStatus.setAccountId(accountId);
        }

        AuthenticationTraking logingAuthTraking = addAuthenticationAttempt(accountAuthenticationStatus.getLogingAuthTraking(), new AuthenticationAttempt(webAuthenticationDetails.getRemoteAddress(), new Date(), AuthenticationAttemptStatus.FAILED));

        // fail count
        logingAuthTraking.setFailCount(logingAuthTraking.getFailCount() + 1);

        // lockout type
        LockoutType lockoutType = null;
        int expectedLockoutDuration = 0;
        Date lockoutExperation = null;

        if (isLockoutEnabled && logingAuthTraking.getFailCount() == totalAttempts) {
            lockoutType = LockoutType.LOCKED;
        } else if (isTemporaryLockoutEnabled) { 
            if (logingAuthTraking.getFailCount() >= lockoutAttempts) {
                lockoutType = LockoutType.TEMPORARY_LOCKED;
                expectedLockoutDuration = lockoutDuration;
            }
            if (logingAuthTraking.getFailCount() >= lockoutAttempts + lockoutAttemptsThreshold) {
                expectedLockoutDuration += lockoutDurationThreshold;
            }
            lockoutExperation = DateUtils.addMinutes(new Date(), expectedLockoutDuration);
        }

        accountAuthenticationStatus.setAccountToken(null);
        accountAuthenticationStatus.setLogingAuthTraking(logingAuthTraking);
        accountAuthenticationStatus.setLockoutExperation(lockoutExperation);
        accountAuthenticationStatus.setLockoutType(lockoutType);
        
        return accountAuthenticationStatusRepository.save(accountAuthenticationStatus);
    }

    /**
     * @param authenticationTraking
     * @param authenticationAttempt
     * @return
     */
    private AuthenticationTraking addAuthenticationAttempt(AuthenticationTraking authenticationTraking, AuthenticationAttempt authenticationAttempt) {
        if (authenticationTraking == null){
            authenticationTraking = new AuthenticationTraking();
        }
        if (authenticationAttempt.getAuthenticationAttemptStatus() == AuthenticationAttemptStatus.SUCCESSFUL) {
            authenticationTraking.setFailCount(0);
        }
        
        List<AuthenticationAttempt> authenticationAttempts = authenticationTraking.getAuthenticationAttempts();
        if (authenticationAttempts == null) {
            authenticationAttempts = new LinkedList<AuthenticationAttempt>();
            authenticationTraking.setAuthenticationAttempts(authenticationAttempts);
        }
        authenticationAttempts.add(0, authenticationAttempt);
        if (authenticationAttempts.size() > historySamples) {
            authenticationAttempts.remove(historySamples);
        }
        return authenticationTraking;
    }

}
