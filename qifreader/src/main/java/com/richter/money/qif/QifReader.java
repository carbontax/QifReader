package com.richter.money.qif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class QifReader extends Reader {

	private final BufferedReader reader;

	private QifType qifType = QifType.UNKNOWN;

	public QifReader(Reader reader) {
		if (reader instanceof BufferedReader) {
			this.reader = (BufferedReader) reader;
		} else {
			this.reader = new BufferedReader(reader);
		}
		readQifType();
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

	public QifReader(File file) throws FileNotFoundException {
		this(new FileReader(file));
	}

	public QifReader(String filename) throws FileNotFoundException {
		this(new File(filename));
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}

	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			String line = reader.readLine();
			Transaction transaction = new Transaction();
			while (line != null) {
				if ("^".equals(line)) {
					transactions.add(transaction);
					transaction = new Transaction();
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

	public void processTransactionDetail(Transaction transaction, String line) {
		char rowType = line.charAt(0);
		String rowData = line.substring(1);
		System.out.println(rowType + "=>" + rowData);
		switch(rowType) {
		case 'D':
			transaction.setDate(rowData);
			break;
		case 'T':
			transaction.setAmount(rowData);
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

	public QifType getQifType() {
		return qifType;
	}

}
