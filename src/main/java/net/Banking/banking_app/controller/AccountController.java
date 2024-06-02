package net.Banking.banking_app.controller;

import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.Banking.banking_app.dto.AccountDto;
import net.Banking.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		
		this.accountService = accountService;
	}
	
	//Add Account REST API
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	//Get Account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//Deposit REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
		double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id,amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//Withdraw REST API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
		double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id,amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//get All Accounts Rest APi
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accountDto=accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
	}
	
	//delete API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		accountService.delete(id);
		return ResponseEntity.ok("Account is deleted successfully!");
	}
	
	
	

}
