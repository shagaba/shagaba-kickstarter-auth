package com.shagaba.kickstarter.auth.common.domain.message;


public interface PayloadMessage<T> extends Message {

    public T getPayload();
    
}
