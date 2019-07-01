package com.bogs696.test.test_netty;

import java.nio.charset.Charset;
import java.util.List;

import com.bogs696.test.data.MessageData;
import com.bogs696.test.data.RequestData;
import com.bogs696.test.data.ResponseData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class ServerDataDecoder extends ReplayingDecoder<Object> {
	private final Charset charset = Charset.forName("UTF-8");

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int code = in.readInt();
		switch (code) {
		case 1:
			ResponseData responseData = new ResponseData();
			responseData.setIntValue(in.readInt());
	        out.add(responseData);
			break;
		case 2:
			RequestData requestData = new RequestData();
			requestData.setIntValue(in.readInt());
			int strLen = in.readInt();
			requestData.setStringValue(in.readCharSequence(strLen, charset).toString());
			out.add(requestData);
			break;
		case 3:
			MessageData messageData = new MessageData();
			int length = in.readInt();
			String message = in.readCharSequence(length, MessageData.getCharset()).toString();
			messageData.setMessage(message);
			out.add(messageData);
			
			break;
		default:
			break;
		}
		
	}

}
