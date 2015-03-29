package com.richter.money.qif.write;

import com.richter.money.qif.QifInvestment;

public class QifInvestmentRecord extends AbstractQifRecord {
	private QifInvestment txn;
	
	public QifInvestmentRecord(QifInvestment txn) {
		this.txn = txn;
	}

	@Override
	public QifHeader getHeader() {
		return new QifInvestmentHeader();
	}

	@Override
	public String formatTransaction() {
		StringBuilder sb = new StringBuilder();
		appendFieldValueToOutput("N", txn.getAction(), sb);
		return sb.toString();
	}
	
}
