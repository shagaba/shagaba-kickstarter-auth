package com.shagaba.kickstarter.auth.common.domain.message;

import java.util.Date;

public class GenericMessage<T> extends SimpleMessage implements PayloadMessage<T>{
    
    protected T payload;
    
    /**
     * @param messageType
     * @param message
     * @param payload
     */
    public GenericMessage(MessageType messageType, String message, T payload) {
        super(messageType, message);
        this.payload = payload;
    }

    /**
     * @param messageType
     * @param code
     * @param message
     * @param payload
     */
    public GenericMessage(MessageType messageType, int code, String message, T payload) {
        super(messageType, code, message);
        this.payload = payload;
    }

    /**
     * @param messageType
     * @param code
     * @param message
     * @param timestamp
     * @param payload
     */
    public GenericMessage(MessageType messageType, int code, String message, Date timestamp, T payload) {
        super(messageType, code, message, timestamp);
        this.payload = payload;
    }

    /**
     * @return the payload
     */
    public T getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

}
