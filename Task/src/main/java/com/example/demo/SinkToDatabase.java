package com.example.demo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SinkToDatabase {

	@Autowired
	private final Logger logger = LoggerFactory.getLogger(SinkToDatabase.class);

	@Autowired
	private BankTransactionHistroyRepository bankTransactionHistroyRepository;

	@Autowired
	private BankBalanceRepository bankBalanceRepository;

	@KafkaListener(topics = "Bank_Balance_info", groupId = "banking_sector")
	public void sinkToAccountDetailsTable(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		BankBalanceInfo balanceInfo = new ObjectMapper().readValue(message, BankBalanceInfo.class);
		bankBalanceRepository.save(balanceInfo);
	}

	@KafkaListener(topics = "Bank_Transaction_details", groupId = "banking_sector")
	@Async("sinkToDatabase")
	public void sinkToTransactionDetailsTable(String message) throws IOException {
		BankTransactionInfo balanceInfo = new ObjectMapper().readValue(message, BankTransactionInfo.class);
		bankTransactionHistroyRepository.save(balanceInfo);
		logger.info(String.format("#### -> Consumed message -> %s", message));

	}
}
