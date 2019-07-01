package com.bogs696.test.data;

import java.nio.charset.Charset;

public class MessageData {
	private static final int CODE = 3;
	private String message;
	private int length;
	private static final Charset CHARSET = Charset.forName("UTF-8");
	public MessageData() {
		setMessage("void message"); 
	}

	public MessageData(String message) {
		super();
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		setLength();
	}
	private void setLength() {
		length = message.length();
	}
	public int getLength() {
		return length;
	}
	public final static synchronized int getCode() {
		return CODE;
	}
	public final static synchronized Charset getCharset() {
		return CHARSET;
	}

}
