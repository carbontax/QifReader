package com.richter.money.qif;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;


public class QifReaderTest {

	public static final String SIMPLE_BANK_QIF = "!Type:Bank\nD03/03/10\nT-379.00\nPCITY OF SPRINGFIELD\n^\n";
	public static final String COMPLEX_QIF = "!Type:Bank\nD6/ 1/94\nT-1,000.00\nN1005\nPBank Of Mortgage\nLlinda\nSlinda\n$-253.64\nSMort Int\n$-746.36\n^\nD6/ 2/94\nT75.00\nPDeposit\n^\nD6/ 3/94\nT-10.00\nPAnthony Hopkins\nMFilm\nLEntertain\nAP.O. Box 27027\nATucson, AZ\nA85726\nA\nA\nA\n^\n";
	public static final String SIMPLE_CASH_QIF = "!Type:Cash\nD03/03/10\nT-379.00\nPCITY OF SPRINGFIELD\n^\n";
	private QifReader testObj;

	@Before
	public void setUp() throws Exception {
		testObj = createQifReader(SIMPLE_BANK_QIF);
	}

	private QifReader createQifReader(String qifString) {
		InputStream buffer = new ByteArrayInputStream(qifString.getBytes());
		return new QifReader(new InputStreamReader(buffer));
	}
	
	@Test
	public void getAccountType_WhenCashAccount() throws Exception {
		testObj = createQifReader(SIMPLE_CASH_QIF);
		assertEquals(QifType.CASH, testObj.getQifType());
	}
	
	@Test
	public void getTransactions() throws Exception {
		assertEquals(QifType.BANK, testObj.getQifType());
		List<Transaction> transactions = testObj.getTransactions();
		assertEquals(1, transactions.size());
		Transaction transaction = transactions.get(0);
		assertEquals(new LocalDate(2010, 3, 3), transaction.getDate());
		assertEquals(new BigDecimal("-379.00"), transaction.getTotal());
		assertEquals("CITY OF SPRINGFIELD", transaction.getPayee());
	}
	
	@Test
	public void getTransactions_WhenComplexQif() throws Exception {
		testObj = createQifReader(COMPLEX_QIF);
		List<Transaction> transactions = testObj.getTransactions();
		assertEquals(3, transactions.size());
		Transaction transaction = transactions.get(0);
		assertEquals(new LocalDate(1994, 6, 1), transaction.getDate());
		assertEquals(new BigDecimal("-1000.00"), transaction.getTotal());
		assertEquals("Bank Of Mortgage", transaction.getPayee());
		assertEquals(2, transaction.getSplits().size());
		transaction = transactions.get(1);
		assertEquals(new LocalDate(1994, 6, 2), transaction.getDate());
		assertEquals(new BigDecimal("75.00"), transaction.getTotal());
		assertEquals("Deposit", transaction.getPayee());
		transaction = transactions.get(2);
		assertEquals(new LocalDate(1994, 6, 3), transaction.getDate());
		assertEquals(new BigDecimal("-10.00"), transaction.getTotal());
		assertEquals("Anthony Hopkins", transaction.getPayee());
	}
}
