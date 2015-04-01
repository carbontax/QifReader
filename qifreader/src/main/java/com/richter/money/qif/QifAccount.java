package com.richter.money.qif;

import java.math.BigDecimal;

import com.richter.money.qif.write.QifAccountTypeEnum;

/**
 * Immutable class represents an Account in QIF specification
 * 
 * @author carbontax@gmail.com
 *
 */
public class QifAccount extends QifTransaction {
	private QifAccountTypeEnum type;
	private String name;
	private String description;
	private BigDecimal balance;

	public QifAccount(QifAccountTypeEnum type, String name, String description,
			BigDecimal balance) {
		super();
		this.type = type;
		this.name = name;
		this.description = description;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public QifAccountTypeEnum getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}	
}
