package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableAsync
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@RestController
	class BankEndpoints {

		@Autowired
		private KafkaProducer KafkaProducer;
		@Autowired
		private BankBalanceRepository bankBalanceRepository;

		@Autowired
		private BankTransactionHistroyRepository bankTransactionHistroyRepository;

		@PostMapping("/sendAccountBalanceToKakfa")
		public void sendAccountBalanceToKakfa(@RequestBody BankBalanceInfo balanceInfo) throws JsonProcessingException {
			System.out.println(balanceInfo.toString());
			ObjectMapper mapper = new ObjectMapper();
			String message = mapper.writeValueAsString(balanceInfo);
			KafkaProducer.sendBalanceInfo(message);
		}

		@PostMapping("/sendAccountBalanceToKakfa")
		public void sendAccountTransactionToKakfa(@RequestBody BankTransactionInfo balanceInfo)
				throws JsonProcessingException {
			System.out.println(balanceInfo.toString());
			ObjectMapper mapper = new ObjectMapper();
			String message = mapper.writeValueAsString(balanceInfo);
			KafkaProducer.sendTransactionInfo(message);
		}

		@GetMapping("/accountBalance/{accountNumber}")
		public BankBalanceInfo getAccountBalance(@PathVariable("accountNumber") String accountNumber) {
			BankBalanceInfo balanceInfo = bankBalanceRepository.findByAccountNumber(accountNumber);

			return balanceInfo;

		}

		@GetMapping("/accountBalance/{transactionType}")
		public List<BankTransactionInfo> getFilterData(@PathVariable("transactionType") String transactionType) {
			List<BankTransactionInfo> BankTransactionInfo = bankTransactionHistroyRepository
					.findByType(transactionType);
			return BankTransactionInfo;
		}
	}

}
