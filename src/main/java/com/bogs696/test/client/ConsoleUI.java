package com.bogs696.test.client;

import java.util.Scanner;

import com.bogs696.test.data.MessageData;
import com.bogs696.test.data.RequestData;
import com.bogs696.test.data.ResponseData;

public class ConsoleUI extends Thread {
	private Scanner scanner;
	private int command;

	public ConsoleUI() {
		scanner = new Scanner(System.in);
		command = 0;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			command = scanner.nextInt();
			switch (command) {
			case 1:
				ServerHelper.instance().getChannelHandlerContext().writeAndFlush(new MessageData("MyMessage"));
				System.out.println("I write server message \"MyMessage\"");
				break;
			case 2:
				RequestData requestData = new RequestData();
				requestData.setIntValue(55);
				requestData.setStringValue("MeStringRequest");
				ServerHelper.instance().getChannelHandlerContext().writeAndFlush(requestData);
				System.out.println("I write server request \"55\"");
				break;
			case 3:
				RequestData requestData2 = new RequestData();
				requestData2.setIntValue(33);
				requestData2.setStringValue("MeStringRequest");
				ServerHelper.instance().getChannelHandlerContext().writeAndFlush(requestData2);
				System.out.println("I write server request \"3\"");
				break;

			default:
				break;
			}
		}

	}

}
