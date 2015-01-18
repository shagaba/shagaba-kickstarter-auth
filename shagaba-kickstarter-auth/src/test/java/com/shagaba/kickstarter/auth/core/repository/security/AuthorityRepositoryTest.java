package com.shagaba.kickstarter.auth.core.repository.security;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.shagaba.kickstarter.auth.core.domain.security.Authority;
import com.shagaba.kickstarter.auth.core.domain.security.AuthorityEntityProducer;
import com.shagaba.kickstarter.auth.core.repository.AbstractRepositoryTest;

public class AuthorityRepositoryTest extends AbstractRepositoryTest<Authority, String> {

	@Resource
	protected AuthorityRepository authorityRepository;
	
	@Resource
	protected AuthorityEntityProducer authorityEntityProducer;

	private Authority testWriteAuthority;
	private Authority testReadAuthority;
	private Authority testDeleteAuthority;
	
	private List<Authority> all;

	@Before
	public void beforeEach() {
		authorityRepository.deleteAll();
		
		testWriteAuthority = authorityEntityProducer.produce("TEST_WRITE_AUTH", "TEST WRITE AUTH");
		testReadAuthority = authorityEntityProducer.produce("TEST_READ_AUTH", "TEST READ AUTH");
		testDeleteAuthority = authorityEntityProducer.produce("TEST_DELETE_AUTH", "TEST DELETE AUTH");
		
		all = authorityRepository.save(Arrays.asList(testWriteAuthority, testReadAuthority, testDeleteAuthority));
	}

	@After
	public void afterEach() {
		authorityRepository.deleteAll();
	}
	
	@Test
	public void findSavedEntity() {
		// exists
		String id = "testId";
		boolean isExists = authorityRepository.exists(id);
		Assert.assertFalse(isExists);

		// CREATE - save
		Authority entity = authorityEntityProducer.produce(id, "TEST");
		Authority saved = authorityRepository.save(entity);
		Assert.assertNotNull(saved);
		Assert.assertNotNull(saved.getId());
		Assert.assertEquals(entity.getId(), saved.getId());
		Assert.assertEquals(entity, saved);

		// exists
		isExists = authorityRepository.exists(id);
		Assert.assertTrue(isExists);

		// READ - findOne
		Authority got = authorityRepository.findOne(saved.getId());
		Assert.assertNotNull(got);
		Assert.assertEquals(saved, got);
	}

	@Test
	public void updateSavedEntity() {
		String id = testReadAuthority.getId();
		// READ - findOne
		Authority got = authorityRepository.findOne(id);
		// UPDATE - save
		authorityEntityProducer.update(got, "UPDATED");
		Authority updated = authorityRepository.save(got);
		Assert.assertNotNull(updated);
		Assert.assertEquals(got, updated);
		Assert.assertNotEquals(testReadAuthority, updated);
	}

	@Test
	public void deleteSavedEntity() {
		String id = testDeleteAuthority.getId();
		boolean isExists = authorityRepository.exists(id);
		Assert.assertTrue(isExists);
		// DELETE -
		authorityRepository.delete(id);
		Authority deleted = authorityRepository.findOne(id);
		Assert.assertNull(deleted);
		// exists
		isExists = authorityRepository.exists(id);
		Assert.assertFalse(isExists);

	}

	@Test
	public void findAll() throws Exception {
		List<Authority> result = authorityRepository.findAll();
		Assert.assertThat(result.size(), Matchers.is(all.size()));
		Assert.assertThat(result.containsAll(all), Matchers.is(true));
	}

}
