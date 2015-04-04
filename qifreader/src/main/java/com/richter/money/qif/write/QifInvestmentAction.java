package com.richter.money.qif.write;

public enum QifInvestmentAction {
	BUY("Buy"), SELL("Sell"), INTEREST_INCOME("IntInc");
	
	private String text;
	
	private QifInvestmentAction(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}