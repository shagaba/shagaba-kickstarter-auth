package com.shagaba.kickstarter.common.service.mapper;

import java.util.List;
import java.util.Set;

public interface MappingService {

	/**
	 * @param object
	 * @param targetClass
	 * @return
	 */
	public <T> T map(Object object, Class<T> targetClass);

	/**
	 * @param object
	 * @param targetObject
	 */
	public <T> void map(Object object, T targetObject);

	/**
	 * @param objects
	 * @param targetElementClass
	 * @return
	 */
	public <O, T> List<T> map(List<O> objects, Class<T> targetElementClass);

	/**
	 * @param objects
	 * @param targets
	 * @param targetElementClass
	 * @return
	 */
	public <O, T> List<T> map(List<O> objects, List<T> targets, Class<T> targetElementClass);

	/**
	 * @param objects
	 * @param targetElementClass
	 * @return
	 */
	public <O, T> Set<T> map(Set<O> objects, Class<T> targetElementClass);

	/**
	 * @param objects
	 * @param targets
	 * @param targetElementClass
	 * @return
	 */
	public <O, T> Set<T> map(Set<O> objects, Set<T> targets, Class<T> targetElementClass);

}