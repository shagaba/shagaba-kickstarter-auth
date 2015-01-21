package com.shagaba.kickstarter.auth.core.domain.security.account;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;

@Component
public class UserAccountEntityGenerator extends AbstractEntityGenerator<UserAccount, String> {

	@Override
	public Class<UserAccount> entityClass() {
		return UserAccount.class;
	}

	@Override
	public void update(UserAccount entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setSalt(tag);
			entity.setEmailAddress(tag);
		}
	}

	@Override
	public String getId(UserAccount entity) {
		return entity.getAccountId();
	}

}
