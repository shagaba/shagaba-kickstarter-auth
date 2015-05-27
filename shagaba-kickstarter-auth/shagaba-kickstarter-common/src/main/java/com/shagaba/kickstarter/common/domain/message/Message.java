package com.shagaba.kickstarter.common.domain.message;

import java.util.Date;

public interface Message {
    
    public MessageType getMessageType();

    public int getCode();

    public String getSubject();
    
    public String getContent();
    
    public Date getTimestamp();
    
}
