package com.shagaba.kickstarter.common.domain.message;

import java.util.Date;

public class GenericMessage<T> extends SimpleMessage implements PayloadMessage<T>{
    
    protected T payload;
    
    /**
     * @param messageType
     * @param content
     * @param payload
     */
    public GenericMessage(MessageType messageType, String message, T payload) {
        super(messageType, message);
        this.payload = payload;
    }

    /**
     * @param messageType
     * @param code
     * @param content
     * @param payload
     */
    public GenericMessage(MessageType messageType, int code, String message, T payload) {
        super(messageType, code, message);
        this.payload = payload;
    }

    /**
     * @param messageType
     * @param code
     * @param content
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("GenericMessage {payload=%s, messageType=%s, code=%s, content=%s, timestamp=%s}", payload, messageType, code,
				content, timestamp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericMessage<?> other = (GenericMessage<?>) obj;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		return true;
	}

}
