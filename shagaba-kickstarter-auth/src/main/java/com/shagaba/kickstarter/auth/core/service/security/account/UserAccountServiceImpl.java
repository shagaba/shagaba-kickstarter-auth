package com.shagaba.kickstarter.auth.core.service.security.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;
import com.shagaba.kickstarter.auth.core.repository.security.account.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    
    @Resource
    protected UserAccountRepository userAccountRepository;
    
    @Override
    public UserAccount getUserAccountByUsername(String username) {
        Assert.notNull(username);
        return userAccountRepository.getUserAccountByUsername(username);
    }

    @Override
    public UserAccount getUserAccountByAccountId(String accountId) {
        Assert.notNull(accountId);
        return userAccountRepository.findOne(accountId);
    }
        
    @Override
    public List<UserAccount> getAllUserAccounts() {
        return Lists.newArrayList(userAccountRepository.findAll());
    }
    
    @Override
    public UserAccount create(UserAccount userAccount){
        Assert.notNull(userAccount);
        Assert.isNull(userAccount.getAccountId());
        return userAccountRepository.save(userAccount);
    }
        
    @Override
    public UserAccount update(UserAccount userAccount){
        Assert.notNull(userAccount);
        Assert.notNull(userAccount.getAccountId());
        return userAccountRepository.save(userAccount);
    }
        
    @Override
    public void delete(String accountId) {
        Assert.notNull(accountId);
        userAccountRepository.delete(accountId);
    }
        
}