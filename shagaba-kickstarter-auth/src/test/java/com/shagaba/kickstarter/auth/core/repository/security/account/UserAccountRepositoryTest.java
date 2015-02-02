package com.shagaba.kickstarter.auth.core.repository.security.account;

import org.junit.Assert;
import org.junit.Test;

import com.shagaba.kickstarter.auth.core.domain.security.account.UserAccount;
import com.shagaba.kickstarter.auth.core.repository.AbstractRepositoryTest;

public class UserAccountRepositoryTest extends AbstractRepositoryTest<UserAccount, String> {

	@Test
	public void getUserAccountByUsername() {
		UserAccountRepository userAccountRepository = (UserAccountRepository) repository;
		UserAccount entity = tstEntities.get(2);
		UserAccount got = userAccountRepository.getUserAccountByUsername(entity.getUsername());
		Assert.assertNotNull(got);
		Assert.assertEquals(entity, got);
	}
}
