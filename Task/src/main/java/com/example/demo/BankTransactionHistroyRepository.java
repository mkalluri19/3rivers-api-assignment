package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BankTransactionHistroyRepository extends CrudRepository<BankTransactionInfo, String>{

	List<BankTransactionInfo> findByType(String type);

}
