package com.bogs696.test.data;

public class RequestData {
	private static final int CODE = 2;
	private int intValue;
	private String stringValue;

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public static synchronized int getCode() {
		return CODE;
	}

}
