package com.shagaba.kickstarter.core.repository.profile;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.core.domain.profile.PersonalInformation;

public interface PersonalInformationRepository extends PagingAndSortingRepository<PersonalInformation, String> {

}
