package com.shagaba.kickstarter.auth.core.repository;

import java.io.Serializable;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shagaba.kickstarter.auth.application.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class }, loader = SpringApplicationContextLoader.class)
public abstract class AbstractRepositoryTest<T, ID extends Serializable> {

	
}
