package com.shagaba.kickstarter.auth.rest.controller.profile;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shagaba.kickstarter.auth.common.service.mapper.MappingService;
import com.shagaba.kickstarter.auth.core.service.profile.PersonalInformationService;
import com.shagaba.kickstarter.auth.rest.domain.profile.PersonalInformation;

@RequestMapping(value = "/personalInformations")
@RestController
public class PersonalInformationController {
    
    @Resource
    protected MappingService mappingService;

    @Resource
    protected PersonalInformationService personalInformationService;
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalInformation create(@Valid @RequestBody PersonalInformation personalInformation) {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = mappingService.map(personalInformation, com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation.class);
        corePersonalInformation = personalInformationService.create(corePersonalInformation);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonalInformation> getAllPersonalInformations() {
        List<com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation> corePersonalInformations = personalInformationService.getAllPersonalInformations();
        return mappingService.map(corePersonalInformations, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{accountId}")
    public PersonalInformation getPersonalInformationById(@PathVariable("accountId") String accountId) throws Exception {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = personalInformationService.getPersonalInformationByAccountId(accountId);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalInformation update(@Valid @RequestBody PersonalInformation personalInformation) throws Exception {
        com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation corePersonalInformation = personalInformationService.getPersonalInformationByAccountId(personalInformation.getAccountId());
        mappingService.map(personalInformation, corePersonalInformation);
        corePersonalInformation = personalInformationService.update(corePersonalInformation);
        return mappingService.map(corePersonalInformation, PersonalInformation.class);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{accountId}")
    public void delete(@PathVariable("accountId") String accountId) {
        personalInformationService.delete(accountId);
    }

    
}