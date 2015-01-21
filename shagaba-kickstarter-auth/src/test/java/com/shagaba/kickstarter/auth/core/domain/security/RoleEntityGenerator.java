package com.shagaba.kickstarter.auth.core.domain.security;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;

@Component
public class RoleEntityGenerator extends AbstractEntityGenerator<Role, String> {

	@Override
	public Class<Role> entityClass() {
		return Role.class;
	}

	@Override
	public void update(Role entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setDescription(tag);
			entity.setAuthorities(Arrays.asList(new String[] { tag }));
		}
	}

	@Override
	public String getId(Role entity) {
		return entity.getId();
	}

}
