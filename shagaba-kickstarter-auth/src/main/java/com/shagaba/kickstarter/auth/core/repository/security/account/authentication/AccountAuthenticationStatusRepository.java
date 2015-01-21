package com.shagaba.kickstarter.auth.core.repository.security.account.authentication;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shagaba.kickstarter.auth.core.domain.security.account.authentication.AccountAuthenticationStatus;

public interface AccountAuthenticationStatusRepository extends PagingAndSortingRepository<AccountAuthenticationStatus, String> {

}
