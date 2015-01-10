package com.shagaba.kickstarter.auth.common.domain.message;

import java.util.Date;

public interface Message {
    
    public MessageType getMessageType();

    public int getCode();

    public String getMessage();
    
    public Date getTimestamp();
    
}
