package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface BankBalanceRepository extends CrudRepository<BankBalanceInfo, String>{

	
	BankBalanceInfo	findByAccountNumber(String accountNumber);
}
