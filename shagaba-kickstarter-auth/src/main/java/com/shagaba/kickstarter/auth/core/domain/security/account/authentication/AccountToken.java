package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import java.util.Date;

public class AccountToken {

    protected String username;

    protected String signature;

    protected Date signatureTimestamp;

    protected Date timestamp;

    /**
     * 
     */
    public AccountToken() {
    }

    /**
     * @param username
     * @param signature
     * @param signatureTimestamp
     * @param timestamp
     */
    public AccountToken(String username, String signature, Date signatureTimestamp, Date timestamp) {
        super();
        this.username = username;
        this.signature = signature;
        this.signatureTimestamp = signatureTimestamp;
        this.timestamp = timestamp;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     *            the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the signatureTimestamp
     */
    public Date getSignatureTimestamp() {
        return signatureTimestamp;
    }

    /**
     * @param signatureTimestamp
     *            the signatureTimestamp to set
     */
    public void setSignatureTimestamp(Date signatureTimestamp) {
        this.signatureTimestamp = signatureTimestamp;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
