package com.shagaba.kickstarter.auth.common.domain.message;

import java.util.Date;

public class SimpleMessage implements Message {
    
    protected MessageType messageType;
    
    protected int code;

    protected String message;
    
    protected Date timestamp;

    /**
     * @param messageType
     * @param message
     */
    public SimpleMessage(MessageType messageType, String message) {
        super();
        this.messageType = messageType;
        this.message = message;
    }

    /**
     * @param messageType
     * @param code
     * @param message
     */
    public SimpleMessage(MessageType messageType, int code, String message) {
        super();
        this.messageType = messageType;
        this.code = code;
        this.message = message;
    }

    /**
     * @param messageType
     * @param code
     * @param message
     * @param timestamp
     */
    public SimpleMessage(MessageType messageType, int code, String message, Date timestamp) {
        super();
        this.messageType = messageType;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * @return the messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
