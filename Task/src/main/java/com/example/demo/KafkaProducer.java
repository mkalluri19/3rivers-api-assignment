package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private static final String TRANSACTION_TOPIC = "Bank_Transaction_details";
	private static final String BALANCE_TOPIC = "Bank_Balance_info";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendBalanceInfo(String message) {
		logger.info(String.format("#### -> Producing Balance Info -> %s", message));
		this.kafkaTemplate.send(BALANCE_TOPIC, message);
	}
	public void sendTransactionInfo(String message) {
		logger.info(String.format("#### -> Producing Transaction Details  -> %s", message));
		this.kafkaTemplate.send(TRANSACTION_TOPIC, message);
	}
	
}
