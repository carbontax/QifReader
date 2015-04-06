package com.richter.money.qif.write;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testPrintCurrency() {
		double amount = 7d;
		assertEquals("Two zeros added", "7.00", Utils.printCurrency(BigDecimal.valueOf(amount)));
		amount = 7.5d;
		assertEquals("One zero added", "7.50", Utils.printCurrency(BigDecimal.valueOf(amount)));
		amount = 7.051d;
		assertEquals("Three decimal places", "7.051", Utils.printCurrency(BigDecimal.valueOf(amount)));
		amount = 7.0517d;
		assertEquals("Four decimal places", "7.0517", Utils.printCurrency(BigDecimal.valueOf(amount)));
		amount = 7.05171d;
		assertEquals("Five decimal places", "7.05171", Utils.printCurrency(BigDecimal.valueOf(amount)));
		amount = 7.051719d;
		assertEquals("Rounded after five decimal places", "7.05172", Utils.printCurrency(BigDecimal.valueOf(amount)));
	}

}
