package com.shagaba.kickstarter.core.repository.security.account.authentication;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.core.domain.security.account.authentication.RememberMeToken;

public interface RememberMeTokenRepository extends PagingAndSortingRepository<RememberMeToken, String> {
	
	public RememberMeToken findBySeries(String series);

	public List<RememberMeToken> findByUsername(String username);
}