package com.shagaba.kickstarter.auth.core.domain.security;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityProducer;

@Component
public class RoleEntityProducer extends AbstractEntityProducer<Role, String>{

	@Override
	public Role produce(String id, String tag) {
		Role entity = new Role();
		entity.setId(id);
		update(entity, tag);
		return entity;
	}

	@Override
	public void update(Role entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setDescription(tag);
			entity.setAuthorities(Arrays.asList(new String[]{tag}));
		}
	}

}
