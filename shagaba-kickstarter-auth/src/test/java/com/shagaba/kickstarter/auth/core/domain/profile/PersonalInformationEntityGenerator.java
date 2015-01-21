package com.shagaba.kickstarter.auth.core.domain.profile;

import org.springframework.stereotype.Component;

import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;

@Component
public class PersonalInformationEntityGenerator extends AbstractEntityGenerator<PersonalInformation, String> {

	@Override
	public Class<PersonalInformation> entityClass() {
		return PersonalInformation.class;
	}

	@Override
	public void update(PersonalInformation entity, String tag) {
		if (tag != null && !tag.isEmpty()) {
			entity.setCompany(tag);
			entity.setDisplayName(tag);
		}
	}

	@Override
	public String getId(PersonalInformation entity) {
		return entity.getAccountId();
	}

}
