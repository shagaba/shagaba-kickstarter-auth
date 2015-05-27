package com.shagaba.kickstarter.rest.controller.profile;

import java.util.List;

import com.shagaba.kickstarter.rest.domain.profile.PersonalInformation;

public interface PersonalInformationController {

	public PersonalInformation create(PersonalInformation personalInformation);

	public List<PersonalInformation> getAllPersonalInformations();

	public PersonalInformation getPersonalInformationById(String accountId) throws Exception;

	public PersonalInformation update(PersonalInformation personalInformation) throws Exception;

	public void delete(String accountId);

}