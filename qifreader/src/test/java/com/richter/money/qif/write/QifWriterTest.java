package com.richter.money.qif.write;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.richter.money.qif.QifAccount;
import com.richter.money.qif.QifInvestment;
import com.richter.money.qif.QifTransaction;

public class QifWriterTest {

	@Test
	public void testEmptyAccountRegister() throws IOException {
		Writer writer = new StringWriter();
		QifWriter qifWriter = new QifWriter(writer);
		List<QifTransaction> txnList = new ArrayList<QifTransaction>();
		QifAccount account = new QifAccount(QifAccountTypeEnum.PORT,
				"Foo Portfolio", "My description", BigDecimal.valueOf(1000.07d));
		qifWriter.write(account, txnList);
		String expected = readTestSampleFile("src/test/resources/writer/invst_account_empty.qif");
		Assert.assertEquals("Test account", expected, writer.toString());
	}

	@Test
	public void testAccountRegister() throws IOException {
		Writer writer = new StringWriter();
		QifWriter qifWriter = new QifWriter(writer);
		List<QifTransaction> txnList = new ArrayList<QifTransaction>();
		QifInvestment invst = buildInvst(10.952d, 100d, 9.95d);
		txnList.add(invst);
		QifAccount account = new QifAccount(QifAccountTypeEnum.PORT,
				"Foo Portfolio", "My description", BigDecimal.valueOf(1000.07d));
		qifWriter.write(account, txnList);

		String expected = readTestSampleFile("src/test/resources/writer/invst_account.qif");
		Assert.assertEquals("Test account", expected, writer.toString());
	}

	/**
	 * http://stackoverflow.com/a/326440/980454
	 */
	private static String readTestSampleFile(String path) throws IOException {
		Charset encoding = StandardCharsets.ISO_8859_1;
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	private static QifInvestment buildInvst(double price, double quantity,
			double commission) {
		QifInvestment invst = new QifInvestment();
		invst.setAction(QifInvestmentAction.BUY.getText());
		invst.setCommission(BigDecimal.valueOf(commission));
		invst.setDate(new LocalDate(2014, 3, 30));
		invst.setMemo("Hello memo");
		invst.setPrice(BigDecimal.valueOf(price));
		invst.setQuantity(BigDecimal.valueOf(quantity));
		invst.setSecurity("Foo Bar Chemicals");
		invst.setTotal(BigDecimal.valueOf((quantity * price) + commission));
		return invst;
	}

}
