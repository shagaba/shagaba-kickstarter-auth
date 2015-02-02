package com.shagaba.kickstarter.auth.core.service.security.account.authentication;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountToken;

@Component
@PropertySource("classpath:userAccount.properties")
public class AccountTokenAuthenticationManager {
    public static final int RADIX = 20;
    
    @Value("${account.authentication.token.server.salt}")
    public String serverSalt;
    
    @Value("${account.authentication.token.active.duration}")
    public int tokenActiveDuration;
    
    @Value("${account.authentication.token.active.threshold.duration}")
    public int tokenActiveThresholdDuration;
    
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    /**
     * @param userAccountDetails
     * @return
     */
    public AccountToken generateToken(UserAccountDetails userAccountDetails) {
        Random random = new SecureRandom();
        long signatureTimestamp  = System.currentTimeMillis() + Math.round(Math.floor(random.nextDouble() * 1000 * tokenActiveThresholdDuration));

        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(userAccountDetails.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(Long.toString(signatureTimestamp, RADIX));
        tokenBuilder.append(":");
        tokenBuilder.append(computeSignature(userAccountDetails, signatureTimestamp));

        AccountToken accountToken = new AccountToken(userAccountDetails.getUsername(), tokenBuilder.toString(), new Date(signatureTimestamp), new Date());
        return accountToken;
    }

    /**
     * @param userAccountDetails
     * @param timestamp
     * @return
     */
    private String computeSignature(UserAccountDetails userAccountDetails, long timestamp) {
        return passwordEncoder.encode(rawSignature(userAccountDetails, timestamp));
    }
    /**
     * @param userAccountDetails
     * @param timestamp
     * @return
     */
    private String rawSignature(UserAccountDetails userAccountDetails, long timestamp) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userAccountDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(timestamp);
        signatureBuilder.append(":");
        signatureBuilder.append(userAccountDetails.getSalt());
        signatureBuilder.append(":");
        signatureBuilder.append(serverSalt);
        signatureBuilder.append(":");
        signatureBuilder.append(userAccountDetails.getAccountId());
        return signatureBuilder.toString();
    }
    
    /**
     * @param authToken
     * @return
     */
    public String getUsername(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }

    /**
     * @param authToken
     * @return
     */
    public Date getTimestamp(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        long timestamp = Long.parseLong(parts[1], RADIX);
        return new Date(timestamp);
    }

    /**
     * @param authToken
     * @param userAccountDetails
     * @return
     */
    public boolean validateToken(String authToken, UserAccountDetails userAccountDetails) {
        if (authToken == null || userAccountDetails == null)
            return false;
        
        String[] parts = authToken.split(":");
        long timestamp = Long.parseLong(parts[1], RADIX);
        String authTokenSignature = parts[2];

        long expires = timestamp + 1000L * 60 * tokenActiveDuration;
        if (expires < System.currentTimeMillis()) {
            return false;
        }

        return this.passwordEncoder.matches(rawSignature(userAccountDetails, timestamp), authTokenSignature);
    }
    
    

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder the passwordEncoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
