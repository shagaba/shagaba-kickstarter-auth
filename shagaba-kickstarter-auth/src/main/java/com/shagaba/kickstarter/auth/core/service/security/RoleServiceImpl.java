package com.shagaba.kickstarter.auth.core.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.shagaba.kickstarter.auth.core.domain.security.Role;
import com.shagaba.kickstarter.auth.core.repository.security.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    protected RoleRepository roleRepository;
    
    @Override
    public Role getRoleById(String id) {
        Assert.notNull(id);
        return roleRepository.findOne(id);
    }
        
    @Override
    public List<Role> getAllRoles(Iterable<String> ids) {
        Assert.notNull(ids);
        return Lists.newArrayList(roleRepository.findAll(ids));
    }
        
    @Override
    public List<Role> getAllRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }
    
    @Override
    public Role create(Role role){
        Assert.notNull(role);
        return roleRepository.save(role);
    }
        
    @Override
    public Role update(Role role){
        Assert.notNull(role);
        Assert.notNull(role.getId());
        return roleRepository.save(role);
    }
        
    @Override
    public void delete(String id) {
        Assert.notNull(id);
        roleRepository.delete(id);
    }
        
}
