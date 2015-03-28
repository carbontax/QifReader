package com.richter.money.qif.write;

import com.richter.money.qif.QifType;

public class QifHeaderFactory {	
	private QifHeaderFactory() {
		// No access
	}

	public static QifHeader forType(QifType type) {
		QifHeader header = null;
		switch (type) {
		case BANK:
			header = new QifBankHeader();
			break;

		default:
			break;
		}
		return header;
	}

}
