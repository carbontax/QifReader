package com.richter.money.qif.write;

import java.io.OutputStream;
import java.util.stream.Stream;

import com.richter.money.qif.QifAccount;
import com.richter.money.qif.QifTransaction;

public interface QifWriter {
	void write(QifAccount account, Stream<QifTransaction> txnSource, OutputStream out);
}
