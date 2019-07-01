package com.bogs696.test.data;

public class ResponseData {
	private static final int CODE = 1;

	private int intValue;
	public ResponseData() {
		
	}
	public ResponseData(int intValue) {
		this.intValue = intValue;
	}
	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public static synchronized int getCode() {
		return CODE;
	}

}
