package net.Banking.banking_app.service;

import java.util.List;

import net.Banking.banking_app.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto account);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);	

	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void delete(Long id);

	

}
