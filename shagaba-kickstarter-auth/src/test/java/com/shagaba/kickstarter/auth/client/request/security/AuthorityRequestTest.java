package com.shagaba.kickstarter.auth.client.request.security;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.shagaba.kickstarter.auth.application.MainApplication;
import com.shagaba.kickstarter.auth.client.common.RestClient;
import com.shagaba.kickstarter.auth.client.common.RestComponents;
import com.shagaba.kickstarter.auth.common.domain.message.Message;
import com.shagaba.kickstarter.auth.common.service.mapper.MappingService;
import com.shagaba.kickstarter.auth.core.common.util.RandomObjectFiller;
import com.shagaba.kickstarter.auth.core.repository.security.AuthorityRepository;
import com.shagaba.kickstarter.auth.rest.domain.security.Authority;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MainApplication.class }, loader = SpringApplicationContextLoader.class)
public class AuthorityRequestTest {
	public static final String ENTITY_ID = "TEST_ID";

	@Autowired
	protected RestComponents restComponents;

	@Autowired
    protected MappingService mappingService;

	@Autowired
	protected AuthorityRepository authorityRepository;

	private Authority tstAuthority = null;

	@Before
	public void beforeEach() {
		RestClient restClient = new RestClient(restComponents);
		Message message = restClient.authenticationRequest().authenticateUser("admin", "admin");
		System.out.println(message.getMessage());
		tstAuthority = RandomObjectFiller.randomObject(Authority.class);
		tstAuthority.setId(ENTITY_ID);
		authorityRepository.save(mappingService.map(tstAuthority, com.shagaba.kickstarter.auth.core.domain.security.Authority.class));
	}

	@After
	public void afterEach() {
		authorityRepository.delete(ENTITY_ID);
	}

	@Test
	public void testCreate() {
		authorityRepository.delete(ENTITY_ID);
		RestClient restClient = new RestClient(restComponents);
		Authority rstAuthority = restClient.authorityRequest().create(tstAuthority);
		Authority repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		Assert.assertThat(rstAuthority, Matchers.is(repAuthority));
	}

	@Test
	public void testGetAllAuthorities() {
		RestClient restClient = new RestClient(restComponents);
		List<Authority> rstAuthorities = Lists.newArrayList(restClient.authorityRequest().getAllAuthorities());
		List<Authority> repAuthorities = mappingService.map(Lists.newArrayList(authorityRepository.findAll()), Authority.class);
		
		Assert.assertThat(rstAuthorities.size(), Matchers.is(repAuthorities.size()));
		Assert.assertThat(rstAuthorities, Matchers.containsInAnyOrder(repAuthorities.toArray(new Authority[repAuthorities.size()])));
	}

	@Test
	public void testGetAuthorityById() {
		RestClient restClient = new RestClient(restComponents);
		Authority rstAuthority = restClient.authorityRequest().getAuthorityById(ENTITY_ID);
		Authority repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		
		Assert.assertThat(rstAuthority, Matchers.is(repAuthority));
	}

	@Test
	public void testUpdate() {
		RestClient restClient = new RestClient(restComponents);
		Authority repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		tstAuthority.setDescription("bla bla");
		Authority updatedAuthority = restClient.authorityRequest().update(tstAuthority);
		
		Assert.assertThat(updatedAuthority, Matchers.is(tstAuthority));
		Assert.assertNotEquals(repAuthority, updatedAuthority);
		
		repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		
		Assert.assertThat(updatedAuthority, Matchers.is(repAuthority));
	}

	@Test
	public void testDelete() {
		RestClient restClient = new RestClient(restComponents);
		Authority repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		Assert.assertNotNull(repAuthority);

		restClient.authorityRequest().delete(ENTITY_ID);
		
		repAuthority = mappingService.map(authorityRepository.findOne(ENTITY_ID), Authority.class);
		Assert.assertNull(repAuthority);
	}

}
