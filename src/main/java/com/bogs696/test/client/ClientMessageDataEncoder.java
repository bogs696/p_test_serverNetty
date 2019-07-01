package com.bogs696.test.client;


import com.bogs696.test.data.MessageData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientMessageDataEncoder extends MessageToByteEncoder<MessageData> {
	 
    @Override
    protected void encode(ChannelHandlerContext ctx, 
    		MessageData msg, ByteBuf out) throws Exception {
    	System.out.println("Message encoder");
    	out.writeInt(MessageData.getCode());
    	out.writeInt(msg.getLength());
        out.writeCharSequence(msg.getMessage(), MessageData.getCharset());
    }
}
