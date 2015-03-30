package com.richter.money.qif;

import java.math.BigDecimal;

public class QifInvestment extends QifTransaction {

	private String action;
	private BigDecimal price;
	private BigDecimal quantity;
	private String security;
	private BigDecimal commission;

	public String getAction() {
		return action;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public String getSecurity() {
		return security;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
}
