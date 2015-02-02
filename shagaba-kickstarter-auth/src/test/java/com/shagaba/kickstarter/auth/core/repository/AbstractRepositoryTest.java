package com.shagaba.kickstarter.auth.core.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shagaba.kickstarter.auth.application.config.ApplicationConfig;
import com.shagaba.kickstarter.auth.core.common.domain.AbstractEntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class }, loader = SpringApplicationContextLoader.class)
public abstract class AbstractRepositoryTest<T, ID extends Serializable> {

	@Autowired
	protected PagingAndSortingRepository<T, ID> repository;
	
	@Autowired
	protected AbstractEntityGenerator<T, ID> entityGenerator;

	protected List<T> tstEntities;
	
	@Before
	public void beforeEach() {
		tstEntities = (List<T>) repository.save(Arrays.asList(entityGenerator.generate("TEST"), entityGenerator.generate("TEST"), entityGenerator.generate("TEST")));
	}

	@After
	public void afterEach() {
		repository.delete(tstEntities);
	}

	@Test
	public void findSavedEntity() {
		// exists
		T entity = entityGenerator.generate("TEST");
		ID id = entityGenerator.getId(entity);
		boolean isExists = repository.exists(id);
		Assert.assertFalse(isExists);

		// CREATE - save
		T saved = repository.save(entity);
		Assert.assertNotNull(saved);
		Assert.assertNotNull(entityGenerator.getId(saved));
		Assert.assertEquals(entityGenerator.getId(entity), entityGenerator.getId(saved));
		Assert.assertEquals(entity, saved);

		// exists
		isExists = repository.exists(id);
		Assert.assertTrue(isExists);

		// READ - findOne
		T got = repository.findOne(entityGenerator.getId(saved));
		Assert.assertNotNull(got);
		Assert.assertEquals(saved, got);
	}

	@Test
	public void updateSavedEntity() {
		ID id = entityGenerator.getId(tstEntities.get(0));
		// READ - findOne
		T got = repository.findOne(id);
		// UPDATE - save
		entityGenerator.update(got, "UPDATED");
		T updated = repository.save(got);
		Assert.assertNotNull(updated);
		Assert.assertEquals(got, updated);
		Assert.assertNotEquals(tstEntities.get(0), updated);
	}

	@Test
	public void deleteSavedEntity() {
		ID id = entityGenerator.getId(tstEntities.get(1));
		boolean isExists = repository.exists(id);
		Assert.assertTrue(isExists);
		// DELETE -
		repository.delete(id);
		T deleted = repository.findOne(id);
		Assert.assertNull(deleted);
		// exists
		isExists = repository.exists(id);
		Assert.assertFalse(isExists);

	}

	@Test
	public void findAll() throws Exception {
		List<T> result = (List<T>) repository.findAll();
		Assert.assertThat(result.size(), Matchers.greaterThan(tstEntities.size()));
		Assert.assertThat(result, Matchers.hasItems(tstEntities.get(0), tstEntities.get(1), tstEntities.get(2)));
	}
	
}
