package com.richter.money.qif.write;

public enum QifHeaderEnum {
	ACCOUNT("!Type:Account"), INVESTMENT("!Type:Invst");
	private String text;

	private QifHeaderEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
