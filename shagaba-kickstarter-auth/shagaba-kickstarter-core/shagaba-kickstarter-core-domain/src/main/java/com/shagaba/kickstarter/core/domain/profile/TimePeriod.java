package com.shagaba.kickstarter.core.domain.profile;

import java.time.LocalTime;

public class TimePeriod {
	
	protected LocalTime startTime;
	
	protected LocalTime endTime;

	/**
	 * 
	 */
	public TimePeriod() {
	}

	/**
	 * @param startTime
	 * @param endTime
	 */
	public TimePeriod(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
