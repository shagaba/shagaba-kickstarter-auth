package com.shagaba.kickstarter.auth.rest.controller.security.account;

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
import com.shagaba.kickstarter.auth.core.service.security.account.UserAccountService;
import com.shagaba.kickstarter.auth.rest.domain.security.account.UserAccount;

@RequestMapping(value = "/security/users")
@RestController
public class UserAccountController {
    
    @Resource
    protected UserAccountService userAccountService;

    @Resource
    protected MappingService mappingService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccount create(@Valid @RequestBody UserAccount userAccount) {
        com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount coreUserAccount = mappingService.map(userAccount, com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount.class);
        coreUserAccount = userAccountService.create(coreUserAccount);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserAccount> getAllUserAccounts() {
        List<com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount> coreUserAccounts = userAccountService.getAllUserAccounts();
        return mappingService.map(coreUserAccounts, UserAccount.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccount getUserAccountById(@PathVariable("id") String id) {
        com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount coreUserAccount = userAccountService.getUserAccountByAccountId(id);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccount update(@Valid @RequestBody UserAccount userAccount) {
        com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount coreUserAccount = mappingService.map(userAccount, com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount.class);
        coreUserAccount = userAccountService.update(coreUserAccount);
        return mappingService.map(coreUserAccount, UserAccount.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") String id) {
        userAccountService.delete(id);
    }
    
}
