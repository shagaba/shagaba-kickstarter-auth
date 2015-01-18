package com.shagaba.kickstarter.auth.core.domain.security;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityProducer;
import com.shagaba.kickstarter.auth.core.domain.security.Authority;

@Component
public class AuthorityEntityProducer extends AbstractEntityProducer<Authority, String>{

	@Override
	public Authority produce(String id, String tag) {
		Authority entity = new Authority();
		entity.setId(id);
		update(entity, tag);
		return entity;
	}

	@Override
	public void update(Authority entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setDescription(tag);
		}
	}

}
