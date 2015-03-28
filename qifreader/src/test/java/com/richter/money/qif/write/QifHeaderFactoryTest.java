package com.richter.money.qif.write;

import org.junit.Assert;
import org.junit.Test;

import com.richter.money.qif.QifType;

public class QifHeaderFactoryTest {

	@Test
	public void testBank() {
		QifHeader bankHeader = QifHeaderFactory.forType(QifType.BANK);
		Assert.assertEquals("!Type:Bank", bankHeader.asHeader());
	}

}
