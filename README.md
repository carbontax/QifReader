QifReader
=========

Java implementation to read QIF files into Transaction object

TODO
====

1. Add Validation of input (eg. Splits must total)
2. Add event handlers to support large QIF inputs
3. Add Memorized Transaction Support
4. Add convert to CSV format

EXAMPLE CODE
============

QifReader reader = new QifReader(filename);
List<QifTransaction> transactions = reader.getTransactions();
for (QifTransaction transaction : transactions) {
	System.out.println(transaction.toString());
}

// Event Driven Approach
QifReader reader = new QifReader(filename);
reader.addListener(new TransactionListener() {
	public void onTransaction(QifTransaction transaction) {
		System.out.println(transaction.toString());
	}
});
