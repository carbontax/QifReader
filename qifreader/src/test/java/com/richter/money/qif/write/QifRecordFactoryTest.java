package com.richter.money.qif.write;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.richter.money.qif.QifInvestment;

public class QifRecordFactoryTest {

	@Test
	public void testInvestment() {
		QifInvestment txn = new QifInvestment();
		txn.setAction("Buy");
		txn.setSecurity("Foo Industries");
		txn.setDate(new LocalDate(2014, 3, 21));
		txn.setPrice(BigDecimal.valueOf(25.66));
		txn.setQuantity(BigDecimal.valueOf(100L));
		txn.setCommission(BigDecimal.valueOf(29.95));
		txn.setMemo("Memo text");
		txn.setTotal(BigDecimal.valueOf(2566.0d + 29.95d));
		QifRecord invstRecord = QifRecordFactory.forTransaction(txn);
		Assert.assertEquals(
				"!Type:Invst\nD3/21/14\nNBuy\nYFoo Industries\nI25.66\nQ100\nT2595.95\nO29.95\nMMemo text\n^\n",
				invstRecord.asFormattedRecord(), "Invst record format");
	}
}
