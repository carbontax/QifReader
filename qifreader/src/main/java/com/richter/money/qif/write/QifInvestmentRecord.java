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
		appendFieldValueToOutput("D", txn.getDate(), sb);
		appendFieldValueToOutput("N", txn.getAction(), sb);
		appendFieldValueToOutput("Y", txn.getSecurity(), sb);
		appendFieldValueToOutput("I", txn.getPrice(), sb);
		appendFieldValueToOutput("Q", txn.getQuantity(), sb);
		appendFieldValueToOutput("T", txn.getTotal(), sb);
		appendFieldValueToOutput("O", txn.getCommission(), sb);
		appendFieldValueToOutput("M", txn.getMemo(), sb);
		return sb.toString();
	}
	
}
