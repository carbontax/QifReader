package com.richter.money.qif;

import java.math.BigDecimal;

public class SplitTransaction {

	private final Transaction transaction;
	private String category;
	private String memo;
	private BigDecimal amount;

	public SplitTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

}