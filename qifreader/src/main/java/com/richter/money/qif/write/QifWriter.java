package com.richter.money.qif.write;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.richter.money.qif.QifAccount;
import com.richter.money.qif.QifTransaction;

public class QifWriter {
	private Writer writer;
	
	public QifWriter(Writer writer) {
		this.writer = writer;
	}
	
	public void write(QifAccount account, List<QifTransaction> txnSource) throws IOException {
		writer.append(new QifAccountRecord(account).asFormattedRecord());
		QifRecordFactory factory = new QifRecordFactory(account);
		QifHeaderEnum header = null;
		for(QifTransaction txn:txnSource) {
			QifRecord record = factory.forTransaction(txn);
			writer.append(record.asFormattedRecord(header));
			header = record.getHeader();
		}
	}
}
