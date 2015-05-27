package com.shagaba.kickstarter.core.domain.profile;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class ContactPerson {
	
    protected String name;

    protected String firstName;

    protected String lastName;

	protected String email;
	
    protected Phone phonePrimary;

    protected Phone phoneSecondary;

    protected Map<DayOfWeek, List<TimePeriod>> availableHours;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phonePrimary
	 */
	public Phone getPhonePrimary() {
		return phonePrimary;
	}

	/**
	 * @param phonePrimary the phonePrimary to set
	 */
	public void setPhonePrimary(Phone phonePrimary) {
		this.phonePrimary = phonePrimary;
	}

	/**
	 * @return the phoneSecondary
	 */
	public Phone getPhoneSecondary() {
		return phoneSecondary;
	}

	/**
	 * @param phoneSecondary the phoneSecondary to set
	 */
	public void setPhoneSecondary(Phone phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	/**
	 * @return the availableHours
	 */
	public Map<DayOfWeek, List<TimePeriod>> getAvailableHours() {
		return availableHours;
	}

	/**
	 * @param availableHours the availableHours to set
	 */
	public void setAvailableHours(Map<DayOfWeek, List<TimePeriod>> availableHours) {
		this.availableHours = availableHours;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("ContactPerson {name=%s, firstName=%s, lastName=%s, email=%s, phonePrimary=%s, phoneSecondary=%s, availableHours=%s}",
						name, firstName, lastName, email, phonePrimary, phoneSecondary, availableHours);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableHours == null) ? 0 : availableHours.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phonePrimary == null) ? 0 : phonePrimary.hashCode());
		result = prime * result + ((phoneSecondary == null) ? 0 : phoneSecondary.hashCode());
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
		ContactPerson other = (ContactPerson) obj;
		if (availableHours == null) {
			if (other.availableHours != null)
				return false;
		} else if (!availableHours.equals(other.availableHours))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phonePrimary == null) {
			if (other.phonePrimary != null)
				return false;
		} else if (!phonePrimary.equals(other.phonePrimary))
			return false;
		if (phoneSecondary == null) {
			if (other.phoneSecondary != null)
				return false;
		} else if (!phoneSecondary.equals(other.phoneSecondary))
			return false;
		return true;
	}


}
