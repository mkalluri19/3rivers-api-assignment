package com.example.demo;

public class BankTransactionInfo {

	
	private String accountNumber;
	private String transactionTs;
	private float balance;
	private  String type;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransactionTs() {
		return transactionTs;
	}
	public void setTransactionTs(String transactionTs) {
		this.transactionTs = transactionTs;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	
	
	
}
