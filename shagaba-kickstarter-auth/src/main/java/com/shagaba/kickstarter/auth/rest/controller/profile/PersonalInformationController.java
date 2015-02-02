package com.shagaba.kickstarter.auth.rest.controller.profile;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shagaba.kickstarter.auth.common.service.mapper.MappingService;
import com.shagaba.kickstarter.auth.core.service.profile.PersonalInformationService;
import com.shagaba.kickstarter.auth.rest.domain.profile.PersonalInformation;

@RestController
@RequestMapping(value = "/personalInformations", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonalInformationController {
    
    @Autowired
    protected MappingService mappingService;

    @Autowired
    protected PersonalInformationService personalInformationService;
    
    @RequestMapping(method = RequestMethod.POST)
    public PersonalInformation create(@Valid @RequestBody PersonalInformation personalInformation) {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = mappingService.map(personalInformation, com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation.class);
        corePersonalInformation = personalInformationService.create(corePersonalInformation);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonalInformation> getAllPersonalInformations() {
        List<com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation> corePersonalInformations = personalInformationService.getAllPersonalInformations();
        return mappingService.map(corePersonalInformations, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public PersonalInformation getPersonalInformationById(@PathVariable("id") String accountId) throws Exception {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = personalInformationService.getPersonalInformationByAccountId(accountId);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public PersonalInformation update(@Valid @RequestBody PersonalInformation personalInformation) throws Exception {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = personalInformationService.getPersonalInformationByAccountId(personalInformation.getAccountId());
        mappingService.map(personalInformation, corePersonalInformation);
        corePersonalInformation = personalInformationService.update(corePersonalInformation);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String accountId) {
        personalInformationService.delete(accountId);
    }

    
}
