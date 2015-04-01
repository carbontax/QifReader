QifReader
=========

Java implementation to read and write QIF files. Records are represented
by Transaction objects.

Features
========
1. Basic Transaction type as a List of Transactions
2. Investment Transaction type
3. Event Handling model to allow for large QIF files with smaller memory foot print

TODO
====

1. Add Validation of input (eg. Splits must total)
2. Add Memorized Transaction Support
3. Separate Event Handling from List Handling

EXAMPLE CODE: READING
=====================

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

Writing QIF Files
=================
Given a data source such as a `List<QifInvestment> txnList`, convert it to QifRecords 
and write them out to a file.

    QifAccount destinationAccount = new QifAccount("My Brokerage Acct");
    OutputStream out = new FileOutputStream(new File("sample.qif"));
    QifWriter writer = new QifWriter(destinationAccount, txnList.stream(), out);
    
    
    
