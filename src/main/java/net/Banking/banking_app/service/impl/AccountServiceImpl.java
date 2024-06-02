package net.Banking.banking_app.service.impl;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import net.Banking.banking_app.dto.AccountDto;
import net.Banking.banking_app.entity.Account;
import net.Banking.banking_app.mapper.AccountMapper;
import net.Banking.banking_app.repository.AccountRepository;
import net.Banking.banking_app.service.AccountService;



@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
    
	public AccountServiceImpl(AccountRepository accountRepository) {		
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount= accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exists") );
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exists") );
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exists") );
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
		
		
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exists") );
		accountRepository.deleteById(id);
	}
	
	
	
	
	
	
	

}
