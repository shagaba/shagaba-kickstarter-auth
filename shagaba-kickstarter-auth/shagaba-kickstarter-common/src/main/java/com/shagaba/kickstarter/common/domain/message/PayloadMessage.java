package com.shagaba.kickstarter.common.domain.message;


public interface PayloadMessage<T> extends Message {

    public T getPayload();
    
}
