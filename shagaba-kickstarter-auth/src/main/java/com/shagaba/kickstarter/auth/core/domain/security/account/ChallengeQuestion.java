package com.shagaba.kickstarter.auth.core.domain.security.account;

public class ChallengeQuestion {

	protected String question;

    protected String answer;

    /**
	 * 
	 */
	public ChallengeQuestion() {
		super();
	}

	/**
	 * @param question
	 * @param answer
	 */
	public ChallengeQuestion(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
