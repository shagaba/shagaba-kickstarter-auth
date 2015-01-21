package com.shagaba.kickstarter.auth.core.repository.profile;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation;

public interface PersonalInformationRepository extends PagingAndSortingRepository<PersonalInformation, String> {

}
