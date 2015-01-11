package com.shagaba.kickstarter.auth.core.repository.profile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation;

@Repository
public interface PersonalInformationRepository extends MongoRepository<PersonalInformation, String> {

}
