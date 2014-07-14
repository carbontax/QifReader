package com.richter.money.qif;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Transaction {

	private LocalDate date;
	private BigDecimal total;
	private String payee;
	private String reference;
	private String clearedStatus;
	private String memo;
	private List<String> address = new ArrayList<String>();
	String category;
	private List<SplitTransaction> splitTransactions = new ArrayList<SplitTransaction>();
	private SplitTransaction currentSplitTransaction;

	public void setDate(String dateString) throws QifReaderException {
		dateString = dateString.replace(" ", "");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("M/d/yy");
		this.setDate(formatter.parseLocalDate(dateString));
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setAmount(String amountString) {
		amountString = amountString.replace(",", "");
		setTotal(new BigDecimal(amountString));
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public String getPayee() {
		return payee;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setClearedStatus(String clearedStatus) {
		this.clearedStatus = clearedStatus;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void addAddress(String address) {
		this.address.add(address);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private void checkSplitTransaction() {
		if (currentSplitTransaction == null) {
			createSplitTransaction();
		}
	}

	private void createSplitTransaction() {
		currentSplitTransaction = new SplitTransaction(this);
		splitTransactions.add(currentSplitTransaction);
	}

	public void setSplitCategory(String splitCategory) {
		checkSplitTransaction();
		if(currentSplitTransaction.getCategory() != null) {
			checkSplitTransaction();
		}
		currentSplitTransaction.setCategory(splitCategory);
	}
	
	public void setSplitMemo(String splitMemo) {
		checkSplitTransaction();
		if(currentSplitTransaction.getMemo() != null) {
			checkSplitTransaction();
		}
		currentSplitTransaction.setMemo(splitMemo);
	}
	
	public void setSplitAmount(String splitAmount) {
		checkSplitTransaction();
		if(currentSplitTransaction.getAmount() != null) {
			checkSplitTransaction();
		}
		currentSplitTransaction.setAmount(new BigDecimal(splitAmount));
	}

	public List<SplitTransaction> getSplits() {
		return splitTransactions;
	}
	
}
