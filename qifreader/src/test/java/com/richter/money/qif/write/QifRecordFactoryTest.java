package com.richter.money.qif.write;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.richter.money.qif.QifInvestment;
import com.richter.money.qif.QifType;

public class QifRecordFactoryTest {

	@Test
	public void testInvestment() {
		QifInvestment txn = new QifInvestment();
		txn.setAction("Buy");
		txn.setSecurity("Foo Industries");
		txn.setDate(new LocalDate(2014, 3, 21));
		txn.setPrice(BigDecimal.valueOf(25.66));
		txn.setQuantity(BigDecimal.valueOf(100L));
		txn.setMemo("Memo text");
		QifRecord invstRecord = QifRecordFactory.forTransaction(txn);
		Assert.assertEquals("!Type:Invst\nD3/21/14\nNBuy\nYFoo Industries\nI25.66\nQ100\nMMemo text\n^\n", invstRecord.asFormattedRecord());
	}}
