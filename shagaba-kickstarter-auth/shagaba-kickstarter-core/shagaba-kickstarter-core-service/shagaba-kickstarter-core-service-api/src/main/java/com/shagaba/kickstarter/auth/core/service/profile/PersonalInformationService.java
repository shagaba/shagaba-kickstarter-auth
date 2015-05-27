package com.shagaba.kickstarter.auth.core.service.profile;

import java.util.List;

import com.shagaba.kickstarter.core.domain.profile.PersonalInformation;

public interface PersonalInformationService {

    public PersonalInformation getPersonalInformationByAccountId(String accountId);

    public List<PersonalInformation> getAllPersonalInformations();

    public PersonalInformation create(PersonalInformation personalInformation);

    public PersonalInformation update(PersonalInformation personalInformation);

    public void delete(String accountId);

}
