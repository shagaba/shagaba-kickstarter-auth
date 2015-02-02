package com.shagaba.kickstarter.auth.application.init;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.shagaba.kickstarter.auth.core.domain.profile.PersonalInformation;
import com.shagaba.kickstarter.auth.core.domain.security.Authority;
import com.shagaba.kickstarter.auth.core.domain.security.Role;
import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;
import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccountRole;
import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.auth.core.repository.profile.PersonalInformationRepository;
import com.shagaba.kickstarter.auth.core.repository.security.AuthorityRepository;
import com.shagaba.kickstarter.auth.core.repository.security.RoleRepository;
import com.shagaba.kickstarter.auth.core.repository.security.account.UserAccountRepository;
import com.shagaba.kickstarter.auth.core.repository.security.account.authentication.AccountAuthenticationStatusRepository;
import com.shagaba.kickstarter.auth.core.service.security.account.authentication.AccountPasswordAuthenticationManager;

public class InitializeMongoDB {
    
    @Autowired
    protected AuthorityRepository authorityRepository;
    
    @Autowired
    protected RoleRepository roleRepository;
    
    @Autowired
    protected UserAccountRepository userAccountRepository;
    
    @Autowired
    protected PersonalInformationRepository personalInformationRepository;
    
    @Autowired
    protected AccountAuthenticationStatusRepository accountAuthenticationStatusRepository;

    @Autowired
    protected AccountPasswordAuthenticationManager accountPasswordAuthenticationManager;
    
    
    @PostConstruct
    public void afterPropertiesSet() {
        roleRepository.deleteAll();
        authorityRepository.deleteAll();
        userAccountRepository.deleteAll();
        personalInformationRepository.deleteAll();
        accountAuthenticationStatusRepository.deleteAll();

        authorityRepository.save(getBasicAuthorities());
        roleRepository.save(getBasicRoles());
        userAccountRepository.save(getBasicUsers());
        personalInformationRepository.save(getPersonalInformations());
        accountAuthenticationStatusRepository.save(getAccountAuthenticationStatus());
    }
    
    
    protected List<Authority> getBasicAuthorities() {
        List<Authority> authorities = new LinkedList<>();
        authorities.add(new Authority("ADMIN_AUTHORITY_SERVICE", "ADMIN AUTHORITY SERVICE"));
        authorities.add(new Authority("ADMIN_ROLE_SERVICE", "ADMIN ROLE SERVICE"));
        return authorities;
    }
    
    protected List<Role> getBasicRoles() {
        List<Role> roles = new LinkedList<>();
        roles.add(new Role("ROLE_VISITOR", "visitor role", Arrays.asList(new String[]{"ROLE_VISITOR"}), null));
        roles.add(new Role("ROLE_USER", "user role", Arrays.asList(new String[]{"ROLE_USER"}), null));
        roles.add(new Role("ROLE_ADMIN", "admin role", Arrays.asList(new String[]{"ROLE_ADMIN"}), null));
        roles.add(new Role("ROLE_ADMIN_AUTHORITY_SERVICE", "admin role on authority service", Arrays.asList(new String[]{"ADMIN_AUTHORITY_SERVICE"}), null));
        roles.add(new Role("ROLE_ADMIN_ROLE_SERVICE", "admin role on role service", Arrays.asList(new String[]{"ADMIN_ROLE_SERVICE"}), null));
        return roles;
    }
    
    protected List<UserAccount> getBasicUsers() {
        List<UserAccount> userAccounts = new LinkedList<>();
        List<UserAccountRole> userAccountRoles = new LinkedList<>();
        userAccountRoles.add(new UserAccountRole("ROLE_ADMIN", null));
        
        String salt = accountPasswordAuthenticationManager.generateSalt();
        String encPassword = accountPasswordAuthenticationManager.encodePassword("admin", salt);
        userAccounts.add(new UserAccount("ADMIN_ID_1", "admin", encPassword, "admin@mycomp.com", null, salt, userAccountRoles));
        
        return userAccounts;
    }
    
    protected List<PersonalInformation> getPersonalInformations() {
        List<PersonalInformation> personalInformations = new LinkedList<>();
        personalInformations.add(new PersonalInformation("ADMIN_ID_1", "Administrator", "first", "last"));
        return personalInformations;
    }
    
    protected List<AccountAuthenticationStatus> getAccountAuthenticationStatus() {
        List<AccountAuthenticationStatus> accountAuthenticationStatus = new LinkedList<>();
        accountAuthenticationStatus.add(new AccountAuthenticationStatus("ADMIN_ID_1", true, true));
        return accountAuthenticationStatus;
    }
    

}
