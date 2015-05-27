package com.shagaba.kickstarter.auth.core.common.domain;

import java.io.Serializable;

import com.shagaba.kickstarter.auth.core.common.util.RandomObjectFiller;

public abstract class AbstractEntityGenerator<T, ID extends Serializable> {
	
	public abstract Class<T> entityClass();

	public abstract void update(T entity, String tag);
	
	public abstract ID getId(T entity);

	protected T generate() {
		return RandomObjectFiller.randomObject(entityClass());
	}

	public T generate(String tag) {
		T entity = generate();
		update(entity, tag);
		System.out.println(entity);
		return entity;
	}

}
