package com.shagaba.kickstarter.auth.core.service.security.account.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.auth.core.security.util.SecurePasswordUtils;

@Component
@PropertySource("classpath:userAccount.properties")
public class AccountPasswordAuthenticationManager {
    
    @Value("${account.authentication.password.server.salt}")
    public String serverSalt;
    
    @Value("${account.password.salt.length}")
    public int passwordSaltLength;
    
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    

    /**
     * @return
     */
    public String generateSalt() {
        return SecurePasswordUtils.generateAlphanumericWithSpechialCharacterPasswoed(passwordSaltLength);
    }

    
    

    /**
     * @param authPassword
     * @param salt
     * @return
     */
    public String encodePassword(String authPassword, String salt) {
        return passwordEncoder.encode(rawPassword(authPassword, salt));
    }
    
    /**
     * @param authPassword
     * @param salt
     * @return
     */
    private String rawPassword(String authPassword, String salt) {
        StringBuilder rpBuilder = new StringBuilder();
        rpBuilder.append(authPassword);
        rpBuilder.append(":");
        rpBuilder.append(salt);
        rpBuilder.append(":");
        rpBuilder.append(serverSalt);
        return rpBuilder.toString();
    }


    /**
     * @param authPassword
     * @param userAccountDetails
     * @return
     */
    public boolean validate(String authPassword, UserAccountDetails userAccountDetails) {
        return this.passwordEncoder.matches(rawPassword(authPassword, userAccountDetails.getSalt()), userAccountDetails.getPassword());
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
