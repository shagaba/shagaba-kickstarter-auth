package com.shagaba.kickstarter.core.repository.security.account.authentication;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.core.domain.security.account.authentication.AccountAuthenticationStatus;

public interface AccountAuthenticationStatusRepository extends PagingAndSortingRepository<AccountAuthenticationStatus, String> {

}
