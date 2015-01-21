package com.shagaba.kickstarter.auth.core.domain.security.account.authentication;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;

@Component
public class AccountAuthenticationStatusEntityGenerator extends AbstractEntityGenerator<AccountAuthenticationStatus, String> {

	@Override
	public Class<AccountAuthenticationStatus> entityClass() {
		return AccountAuthenticationStatus.class;
	}

	@Override
	public void update(AccountAuthenticationStatus entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.getAccountToken().setSignature(tag);
			entity.getAccountToken().setUsername(tag);
		}
	}

	@Override
	public String getId(AccountAuthenticationStatus entity) {
		return entity.getAccountId();
	}

}
