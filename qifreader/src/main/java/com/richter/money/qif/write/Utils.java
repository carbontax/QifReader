package com.richter.money.qif.write;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.richter.money.qif.QifReader;

public class Utils {
	public static DateTimeFormatter formatter = DateTimeFormat.forPattern(QifReader.DEFAULT_DATE_FORMAT);
}
