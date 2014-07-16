package com.richter.money.qif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class QifReader extends Reader {

	public static final String DEFAULT_DATE_FORMAT = "M/d/yy";
	private QifType qifType = QifType.UNKNOWN;
	private final BufferedReader reader;
	private DateTimeFormatter formatter = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT);

	public QifReader(File file) throws FileNotFoundException {
		this(new FileReader(file));
	}

	public QifReader(Reader reader) {
		if (reader instanceof BufferedReader) {
			this.reader = (BufferedReader) reader;
		} else {
			this.reader = new BufferedReader(reader);
		}
		readQifType();
	}

	public QifReader(String filename) throws FileNotFoundException {
		this(new File(filename));
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
	
	public List<QifInvestment> getInvestments() {
		List<QifInvestment> investments = new ArrayList<QifInvestment>();
		try {
			String line = reader.readLine();
			QifInvestment investment = new QifInvestment();
			while (line != null) {
				if ("^".equals(line)) {
					investments.add(investment);
					investment = new QifInvestment();
				} else {
					processInvestmentDetail(investment, line);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			throw new QifReaderException(e);
		}
		return investments;
	}

	public QifType getQifType() {
		return qifType;
	}

	public List<QifCashTransaction> getTransactions() {
		List<QifCashTransaction> transactions = new ArrayList<QifCashTransaction>();
		try {
			String line = reader.readLine();
			QifCashTransaction transaction = new QifCashTransaction();
			while (line != null) {
				if ("^".equals(line)) {
					transactions.add(transaction);
					transaction = new QifCashTransaction();
				} else {
					processTransactionDetail(transaction, line);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			throw new QifReaderException(e);
		}
		return transactions;
	}

	public void processInvestmentDetail(QifInvestment investment, String line) {
		char rowType = line.charAt(0);
		String rowData = line.substring(1);
		switch(rowType) {
		case 'D':
			setDate(investment, rowData);
			break;
		case 'N':
			investment.setAction(rowData);
			break;
		case 'Y':
			investment.setSecurity(rowData);
			break;
		case 'I':
			setPrice(investment, rowData);
			break;
		case 'Q':
			setQuantity(investment, rowData);
			break;
		case 'T':
		case '$':
			setTotal(investment, rowData);
			break;
		case 'M':
			investment.setMemo(rowData);
			break;
		}
		
	}
	
	//D	Date
	//T	Amount
	//C	Cleared status
	//N	Num (check or reference number)
	//P	Payee
	//M	Memo
	//A	Address (up to five lines;the sixth line is an optional message)
	//L	Category (Category/Subcategory/Transfer/Class)
	//S	Category in split (Category/Transfer/Class)
	//E	Memo in split
	//$	Dollar amount of split
	//^	End of entry
	public void processTransactionDetail(QifCashTransaction transaction, String line) {
		char rowType = line.charAt(0);
		String rowData = line.substring(1);
		switch(rowType) {
		case 'D':
			setDate(transaction, rowData);
			break;
		case 'T':
			setTotal(transaction, rowData);
			break;
		case 'N':
			transaction.setReference(rowData);
			break;
		case 'C':
			transaction.setClearedStatus(rowData);
			break;
		case 'P':
			transaction.setPayee(rowData);
			break;
		case 'M':
			transaction.setMemo(rowData);
			break;
		case 'A':
			transaction.addAddress(rowData);
			break;
		case 'L':
			transaction.setCategory(rowData);
			break;
		case 'S':
			transaction.setSplitCategory(rowData);
			break;
		case 'E':
			transaction.setSplitMemo(rowData);
			break;
		case '$':
			transaction.setSplitAmount(rowData);
			break;
		}
		
	}
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}

	private void readQifType() {
		try {
			String line = this.reader.readLine();
			if (line.startsWith("!Type:")) {
				this.qifType = QifType.valueOf(line.substring(6).toUpperCase());
			} else {
				throw new QifReaderException("File Format is not a QIF format");
			}
		} catch (QifReaderException e) {
			throw e;
		} catch (Exception e) {
			throw new QifReaderException(e);
		}
	}

	private void setTotal(QifTransaction transaction, String totalString) {
		totalString = totalString.replace(",", "");
		transaction.setTotal(new BigDecimal(totalString));
	}
	
	private void setDate(QifTransaction transaction, String dateString) throws QifReaderException {
		dateString = dateString.replace(" ", "");
		transaction.setDate(formatter.parseLocalDate(dateString));
	}

	private void setPrice(QifInvestment investment, String priceString) {
		priceString = priceString.replace(",", "");
		investment.setPrice(new BigDecimal(priceString));
	}

	private void setQuantity(QifInvestment investment, String quantityString) {
		quantityString = quantityString.replace(",", "");
		investment.setQuantity(new BigDecimal(quantityString));
	}

	public void setDateFormat(String dateFormat) {
		formatter = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT);
	}

}
