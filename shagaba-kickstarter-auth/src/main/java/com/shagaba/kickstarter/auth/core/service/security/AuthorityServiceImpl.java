package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.shagaba.kickstarter.auth.core.domain.security.Authority;
import com.shagaba.kickstarter.auth.core.repository.security.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    
    @Resource
    protected AuthorityRepository authorityRepository;
    
    @Override
    public Authority getAuthorityById(String id) {
        Assert.notNull(id);
        return authorityRepository.findOne(id);
    }
        
    @Override
    public List<Authority> getAllAuthorities(Iterable<String> ids) {
        Assert.notNull(ids);
        return Lists.newArrayList(authorityRepository.findAll(ids));
    }
        
     @Override
    public List<Authority> getAllAuthorities() {
        return Lists.newArrayList(authorityRepository.findAll());
    }
    
    @Override
    public Authority create(Authority authority){
        Assert.notNull(authority);
        return authorityRepository.save(authority);
    }
        
    @Override
    public Authority update(Authority authority){
        Assert.notNull(authority);
        Assert.notNull(authority.getId());
        return authorityRepository.save(authority);
    }
        
    @Override
    public void delete(String id) {
        Assert.notNull(id);
        authorityRepository.delete(id);
    }
        
}
