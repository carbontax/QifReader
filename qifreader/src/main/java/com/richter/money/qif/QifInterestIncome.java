package com.richter.money.qif;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.richter.money.qif.write.QifAccountTypeEnum;
import com.richter.money.qif.write.QifInvestmentAction;

/**
 * Immutable class represents an Account in QIF specification
 * 
 * @author carbontax@gmail.com
 *
 */
public class QifInterestIncome extends QifTransaction {
	private QifAccountTypeEnum type;

	public QifInterestIncome(QifAccountTypeEnum type, LocalDate date, BigDecimal total) {
		super();
		this.type = type;
		this.setDate(date);
		this.setTotal(total);
	}

	public QifAccountTypeEnum getType() {
		return type;
	}
	
	public String getAction() {
		return QifInvestmentAction.INTEREST_INCOME.getText();
	}
}
