package com.richter.money.qif.write;

import com.richter.money.qif.QifAccount;

public class QifAccountRecord extends AbstractQifRecord {
	private QifAccount txn;
	
	public QifAccountRecord(QifAccount txn) {
		this.txn = txn;
	}

	@Override
	public QifHeader getHeader() {
		return new QifHeader() {
			@Override
			public String toString() {
				return "!Type:Account";
			}
		};
	}

	@Override
	public String formatTransaction() {
		StringBuilder sb = new StringBuilder();
		appendFieldValueToOutput("N", txn.getName(), sb);
		appendFieldValueToOutput("D", txn.getDescription(), sb);
		appendFieldValueToOutput("T", txn.getType().getLabel(), sb);
		appendFieldValueToOutput("B", txn.getBalance(), sb);
		return sb.toString();
	}
	
}