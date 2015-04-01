package com.richter.money.qif.write;

import com.richter.money.qif.QifAccount;
import com.richter.money.qif.QifInvestment;
import com.richter.money.qif.QifReaderException;
import com.richter.money.qif.QifTransaction;

public class QifRecordFactory {
	private QifRecordFactory() {
		// No access
	}

	public static QifRecord forTransaction(QifTransaction txn) {
		QifRecord record = null;
		if (txn instanceof QifInvestment) {
			QifInvestment invst = (QifInvestment) txn;
			record = new QifInvestmentRecord(invst);
		} else if (txn instanceof QifAccount) {
			record = new QifAccountRecord((QifAccount) txn);
		} else {
			throw new QifReaderException("Transaction type not implemented");
		}
		return record;
	}

}
