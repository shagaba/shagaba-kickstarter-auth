package com.shagaba.kickstarter.common.domain.message;

import java.util.Date;

public class SimpleMessage implements Message {
    
	protected MessageType messageType;
    
    protected int code;

    protected String subject;
    
    protected String content;
    
    protected Date timestamp;

    /**
	 * 
	 */
	public SimpleMessage() {
	}

    /**
     * @param messageType
     * @param subject
     */
    public SimpleMessage(MessageType messageType, String subject) {
        super();
        this.messageType = messageType;
        this.subject = subject;
    }

    /**
     * @param messageType
     * @param code
     * @param subject
     */
    public SimpleMessage(MessageType messageType, int code, String subject) {
        super();
        this.messageType = messageType;
        this.code = code;
        this.subject = subject;
    }

    /**
     * @param messageType
     * @param code
     * @param subject
     * @param timestamp
     */
    public SimpleMessage(MessageType messageType, int code, String subject, Date timestamp) {
        super();
        this.messageType = messageType;
        this.code = code;
        this.subject = subject;
        this.timestamp = timestamp;
    }

    /**
     * @param messageType
     * @param code
     * @param subject
     * @param content
     * @param timestamp
     */
    public SimpleMessage(MessageType messageType, int code, String subject, String content, Date timestamp) {
        super();
        this.messageType = messageType;
        this.code = code;
        this.subject = subject;
        this.content = content;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
		return String.format("SimpleMessage {messageType=%s, code=%s, subject=%s, content=%s, timestamp=%s}", messageType, code, subject,
				content, timestamp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (messageType != other.messageType)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
