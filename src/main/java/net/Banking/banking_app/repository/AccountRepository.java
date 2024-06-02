package net.Banking.banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.Banking.banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
