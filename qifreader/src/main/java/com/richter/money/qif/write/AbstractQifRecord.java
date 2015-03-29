package com.richter.money.qif.write;


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
	
	protected boolean isNotEmpty(Object field) {
		if(field instanceof String) {
			return isNotEmptyString((String) field); 
		}
		return false;
	}
	
	private boolean isNotEmptyString(String field) {
		if(field == null || field.isEmpty()) {
			return false;
		}
		return true;		
	}
	
	protected void appendFieldValueToOutput(String key, Object value, StringBuilder sb) {
		if(isNotEmpty(value)) {
			if(sb.length() > 0) { sb.append(LINE_SEPARATOR); }
			sb.append(key).append(value);
		}
	}

}
