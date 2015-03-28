package com.richter.money.qif.write;

import com.richter.money.qif.QifType;


public class QifBankHeader implements QifHeader {
	private QifType type = QifType.BANK;
	
	public QifBankHeader() {
		
	}

	public String asHeader() {
		return "!Type:Bank";
	}

	@Override
	public String toString() {
		return "QifBankHeader [type=" + type + "]";
	}	
	
}
