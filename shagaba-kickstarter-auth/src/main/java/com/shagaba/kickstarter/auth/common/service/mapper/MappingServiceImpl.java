package com.shagaba.kickstarter.auth.common.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    protected DozerBeanMapper dozerBeanMapper;

    @Override
	public <T> T map(Object object, Class<T> targetClass) {
        if (object == null)
            return null;
        return dozerBeanMapper.map(object, targetClass);
    }

    @Override
	public <T> void map(Object object, T targetObject) {
        if (object == null)
            targetObject = null;
        dozerBeanMapper.map(object, targetObject);
    }

    @Override
	public <O, T> List<T> map(List<O> objects, Class<T> targetElementClass) {
        if (objects == null)
            return null;
        List<T> targets = Lists.newArrayList();
        return map(objects, targets, targetElementClass);
    }
    
    @Override
	public <O, T> List<T> map(List<O> objects, List<T> targets, Class<T> targetElementClass) {
        if (objects == null)
            return null;
        return (List<T>) internalMap(objects, targets, targetElementClass);
    }

    @Override
	public <O, T> Set<T> map(Set<O> objects, Class<T> targetElementClass) {
        if (objects == null)
            return null;
        Set<T> targets = Sets.newHashSet();
        return map(objects, targets, targetElementClass);
    }
    
    @Override
	public <O, T> Set<T> map(Set<O> objects, Set<T> targets, Class<T> targetElementClass) {
        if (objects == null)
            return null;
       return (Set<T>) internalMap(objects, targets, targetElementClass);
    }

    /**
     * @param objects
     * @param targets
     * @param targetElementClass
     * @return
     */
    private <O, T> Collection<T> internalMap(Collection<O> objects, Collection<T> targets, Class<T> targetElementClass) {
        if (objects == null)
            return null;
        for (O o : objects) {
            targets.add(dozerBeanMapper.map(o, targetElementClass));
        }
        return targets;
    }
}
