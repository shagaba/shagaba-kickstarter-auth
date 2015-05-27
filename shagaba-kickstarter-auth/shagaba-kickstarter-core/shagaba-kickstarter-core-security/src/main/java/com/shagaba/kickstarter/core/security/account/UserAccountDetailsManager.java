package com.shagaba.kickstarter.core.security.account;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.core.domain.security.Role;
import com.shagaba.kickstarter.core.domain.security.account.UserAccount;
import com.shagaba.kickstarter.core.domain.security.account.UserAccountDetails;
import com.shagaba.kickstarter.core.domain.security.account.UserAccountRole;
import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountAuthenticationStatus;
import com.shagaba.kickstarter.core.repository.security.RoleRepository;
import com.shagaba.kickstarter.core.repository.security.account.UserAccountRepository;
import com.shagaba.kickstarter.core.security.account.authentication.AccountAuthenticationStatusManager;

@Component
public class UserAccountDetailsManager implements UserDetailsService {

    @Autowired
    protected RoleRepository roleRepository;
    
    @Autowired
    protected UserAccountRepository userAccountRepository;
    
    @Autowired
    protected AccountAuthenticationStatusManager accountAuthenticationStatusManager;
    
    @Override
    public UserAccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.getUserAccountByUsername(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("The user was not found");
        }
        
        AccountAuthenticationStatus accountAuthenticationStatus = accountAuthenticationStatusManager.getAccountAuthenticationStatusByAccountId(userAccount.getAccountId());
        boolean isEnabled = accountAuthenticationStatus.isRegistered();
        boolean isAccountNonExpired = true;
        boolean isCredentialsNonExpired = true;
        boolean isAccountNonLocked = accountAuthenticationStatus.getLockoutType() == null;
        
        List<GrantedAuthority> grantedAuthorities = new LinkedList<GrantedAuthority>();
        List<UserAccountRole> userAccountRoles = userAccount.getRoles();

        if (userAccountRoles != null) {
            List<String> roleIds = new LinkedList<>();
            for (UserAccountRole userAccountRole : userAccountRoles) {
                if (userAccountRole.getExperationTime() == null || userAccountRole.getExperationTime().getTime() > System.currentTimeMillis()) {
                    roleIds.add(userAccountRole.getRoleId());
                }
            }
            
            Iterable<Role> roles = roleRepository.findAll(roleIds);
            if (roles != null) {
                for (Role role : roles) {
                    List<String> authorities = role.getAuthorities();
                    if (authorities != null) {
                        for (String authority : authorities) {
                            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                        }
                    }
                }
            }
            
        }
        
        if (grantedAuthorities.isEmpty()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
        }
 
        return new UserAccountDetails(userAccount.getAccountId(), userAccount.getUsername(), userAccount.getPassword(), userAccount.getSalt(), isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, grantedAuthorities);
    }

}
