package com.shagaba.kickstarter.auth.core.common.domain;

import java.io.Serializable;

public abstract class AbstractEntityProducer <T, ID extends Serializable> {
	
	public abstract T produce(ID id, String tag);

	public abstract void update(T entity, String tag);

}
