package com.shagaba.kickstarter.auth.common.domain.message;

import java.util.Date;

public class SimpleMessage implements Message {
    
	protected MessageType messageType;
    
    protected int code;

    protected String message;
    
    protected Date timestamp;

    /**
	 * 
	 */
	public SimpleMessage() {
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SimpleMessage {messageType=%s, code=%s, message=%s, timestamp=%s}", messageType, code, message, timestamp);
	}
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleMessage other = (SimpleMessage) obj;
		if (code != other.code)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageType != other.messageType)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
