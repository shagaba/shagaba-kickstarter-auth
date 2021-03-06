package com.shagaba.kickstarter.rest.controller.security.account;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shagaba.kickstarter.auth.core.service.security.account.UserAccountService;
import com.shagaba.kickstarter.common.service.mapper.MappingService;
import com.shagaba.kickstarter.rest.controller.security.account.UserAccountController;
import com.shagaba.kickstarter.rest.domain.security.account.UserAccount;

@RestController
@RequestMapping(value = "/security/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAccountControllerImpl implements UserAccountController {
    
    @Autowired
    protected UserAccountService userAccountService;

    @Autowired
    protected MappingService mappingService;

    @RequestMapping(method = RequestMethod.POST)
    public UserAccount create(@Valid @RequestBody UserAccount userAccount) {
        com.shagaba.kickstarter.core.domain.security.account.UserAccount coreUserAccount = mappingService.map(userAccount, com.shagaba.kickstarter.core.domain.security.account.UserAccount.class);
        coreUserAccount = userAccountService.create(coreUserAccount);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserAccount> getAllUserAccounts() {
        List<com.shagaba.kickstarter.core.domain.security.account.UserAccount> coreUserAccounts = userAccountService.getAllUserAccounts();
        return mappingService.map(coreUserAccounts, UserAccount.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserAccount getUserAccountById(@PathVariable("id") String id) {
        com.shagaba.kickstarter.core.domain.security.account.UserAccount coreUserAccount = userAccountService.getUserAccountByAccountId(id);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserAccount update(@Valid @RequestBody UserAccount userAccount) {
        com.shagaba.kickstarter.core.domain.security.account.UserAccount coreUserAccount = userAccountService.getUserAccountByAccountId(userAccount.getId());
        mappingService.map(userAccount, coreUserAccount);
        coreUserAccount = userAccountService.update(coreUserAccount);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        userAccountService.delete(id);
    }
    
}
