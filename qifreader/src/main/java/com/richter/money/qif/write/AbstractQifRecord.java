package com.richter.money.qif.write;

import java.math.BigDecimal;

import org.joda.time.LocalDate;


public abstract class AbstractQifRecord implements QifRecord {
	public final static String LINE_SEPARATOR = "\n";
	public final static String END_OF_RECORD = "^";

	public String asFormattedRecord() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader()).append(LINE_SEPARATOR);

		sb.append(formatTransaction()).append(LINE_SEPARATOR);

		sb.append(END_OF_RECORD).append(LINE_SEPARATOR);
		return sb.toString();
	}

	public abstract QifHeader getHeader();

	public abstract String formatTransaction();
	
	private void appendStringValueToOutput(String key, String value, StringBuilder sb) {
		if(value != null && !value.isEmpty()) {
			if(sb.length() > 0) { sb.append(LINE_SEPARATOR); }
			sb.append(key).append(value);
		}
	}
	
	private void appendDateValueToOutput(String key, LocalDate date, StringBuilder sb) {
		if(date != null) {
			if(sb.length() > 0) { sb.append(LINE_SEPARATOR); }
			String value = Utils.formatter.print(date);
			sb.append(key).append(value);
		}
	}
	
	private void appendBigDecimalValueToOutput(String key, BigDecimal value, StringBuilder sb) {
		if(value != null) {
			if(sb.length() > 0) { sb.append(LINE_SEPARATOR); }
			sb.append(key).append(value);
		}
	}
	
	protected void appendFieldValueToOutput(String key, Object value, StringBuilder sb) {
		if(value instanceof String) {
			appendStringValueToOutput(key, (String) value, sb);
		} else if (value instanceof LocalDate) {
			appendDateValueToOutput(key, (LocalDate) value, sb);
		} else if (value instanceof BigDecimal) {
			appendBigDecimalValueToOutput(key, (BigDecimal) value, sb);
		}
	}

}
