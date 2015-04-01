package com.richter.money.qif.write;

/**
 * Account types in QIF specification
 * 
 * @author carbontax@gmail.com
 *
 */
public enum QifAccountTypeEnum {
	PORT("Port");
	
	private String label;
	
	private QifAccountTypeEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
