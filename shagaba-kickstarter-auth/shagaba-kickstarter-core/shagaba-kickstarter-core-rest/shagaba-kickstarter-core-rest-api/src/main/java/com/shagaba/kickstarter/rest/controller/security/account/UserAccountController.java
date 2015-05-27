package com.shagaba.kickstarter.rest.controller.security.account;

import java.util.List;

import com.shagaba.kickstarter.rest.domain.security.account.UserAccount;

public interface UserAccountController {

	public UserAccount create(UserAccount userAccount);

	public List<UserAccount> getAllUserAccounts();

	public UserAccount getUserAccountById(String id);

	public UserAccount update(UserAccount userAccount);

	public void delete(String id);

}