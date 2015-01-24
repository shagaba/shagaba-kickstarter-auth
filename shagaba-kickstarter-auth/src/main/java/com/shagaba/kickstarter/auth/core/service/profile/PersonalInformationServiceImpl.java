package com.shagaba.kickstarter.auth.core.service.profile;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation;
import com.shagaba.kickstarter.auth.core.repository.profile.PersonalInformationRepository;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {
    
    @Resource
    protected PersonalInformationRepository personalInformationRepository;
    
    @Override
    public PersonalInformation getPersonalInformationByAccountId(String accountId) {
        Assert.notNull(accountId);
        return personalInformationRepository.findOne(accountId);
    }
        
    @Override
    public List<PersonalInformation> getAllPersonalInformations() {
        return Lists.newArrayList(personalInformationRepository.findAll());
    }
    
    @Override
    public PersonalInformation create(PersonalInformation personalInformation){
        Assert.notNull(personalInformation);
        Assert.notNull(personalInformation.getAccountId());
        return personalInformationRepository.save(personalInformation);
    }
        
    @Override
    public PersonalInformation update(PersonalInformation personalInformation){
        Assert.notNull(personalInformation);
        Assert.notNull(personalInformation.getAccountId());
        return personalInformationRepository.save(personalInformation);
    }
        
    @Override
    public void delete(String accountId) {
        Assert.notNull(accountId);
        personalInformationRepository.delete(accountId);
    }
        
}
