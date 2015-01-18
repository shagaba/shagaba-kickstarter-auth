package com.shagaba.kickstarter.auth.core.repository.security;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.shagaba.kickstarter.auth.core.domain.security.Role;
import com.shagaba.kickstarter.auth.core.domain.security.RoleEntityProducer;
import com.shagaba.kickstarter.auth.core.repository.AbstractRepositoryTest;

public class RoleRepositoryTest extends AbstractRepositoryTest<Role, String> {

	@Resource
	protected RoleRepository roleRepository;
	
	@Resource
	protected RoleEntityProducer roleEntityProducer;

	private Role testAdminRole;
	private Role testUserRole;
	private Role testVisitorRole;
	
	private List<Role> all;

	@Before
	public void beforeEach() {
		roleRepository.deleteAll();
		
		testAdminRole = roleEntityProducer.produce("TEST_ADMIN_ROLE", "TEST ADMIN ROLE");
		testUserRole = roleEntityProducer.produce("TEST_USER_ROLE", "TEST USER ROLE");
		testVisitorRole = roleEntityProducer.produce("TEST_VISITOR_ROLE", "TEST VISITOR ROLE");
		
		all = roleRepository.save(Arrays.asList(testAdminRole, testUserRole, testVisitorRole));
	}

	@After
	public void afterEach() {
		roleRepository.deleteAll();
	}
	
	@Test
	public void findSavedEntity() {
		// exists
		String id = "testId";
		boolean isExists = roleRepository.exists(id);
		Assert.assertFalse(isExists);

		// CREATE - save
		Role entity = roleEntityProducer.produce(id, "TEST");
		Role saved = roleRepository.save(entity);
		Assert.assertNotNull(saved);
		Assert.assertNotNull(saved.getId());
		Assert.assertEquals(entity.getId(), saved.getId());
		Assert.assertEquals(entity, saved);

		// exists
		isExists = roleRepository.exists(id);
		Assert.assertTrue(isExists);

		// READ - findOne
		Role got = roleRepository.findOne(saved.getId());
		Assert.assertNotNull(got);
		Assert.assertEquals(saved, got);
	}

	@Test
	public void updateSavedEntity() {
		String id = testUserRole.getId();
		// READ - findOne
		Role got = roleRepository.findOne(id);
		// UPDATE - save
		roleEntityProducer.update(got, "UPDATED");
		Role updated = roleRepository.save(got);
		Assert.assertNotNull(updated);
		Assert.assertEquals(got, updated);
		Assert.assertNotEquals(testUserRole, updated);
	}

	@Test
	public void deleteSavedEntity() {
		String id = testVisitorRole.getId();
		boolean isExists = roleRepository.exists(id);
		Assert.assertTrue(isExists);
		// DELETE -
		roleRepository.delete(id);
		Role deleted = roleRepository.findOne(id);
		Assert.assertNull(deleted);
		// exists
		isExists = roleRepository.exists(id);
		Assert.assertFalse(isExists);

	}

	@Test
	public void findAll() throws Exception {
		List<Role> result = roleRepository.findAll();
		Assert.assertThat(result.size(), Matchers.is(all.size()));
		Assert.assertThat(result.containsAll(all), Matchers.is(true));
	}


}
