package com.shagaba.kickstarter.auth.core.domain.security;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;
import com.shagaba.kickstarter.auth.core.domain.security.Authority;

@Component
public class AuthorityEntityGenerator extends AbstractEntityGenerator<Authority, String> {

	@Override
	public Class<Authority> entityClass() {
		return Authority.class;
	}

	@Override
	public void update(Authority entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setDescription(tag);
		}
	}

	@Override
	public String getId(Authority entity) {
		return entity.getId();
	}

}
