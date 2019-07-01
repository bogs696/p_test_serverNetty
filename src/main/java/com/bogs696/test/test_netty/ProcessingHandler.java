package com.bogs696.test.test_netty;

import com.bogs696.test.data.MessageData;
import com.bogs696.test.data.RequestData;
import com.bogs696.test.data.ResponseData;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		Clients.instance().add(ctx);
		System.out.println("Handler added");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		Clients.instance().remove(ctx);
		System.out.println("Handler removed");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof RequestData) {
			RequestData requestData = (RequestData) msg;
			ResponseData responseData = new ResponseData();
			responseData.setIntValue(requestData.getIntValue() * 2);
			ChannelFuture future = ctx.writeAndFlush(responseData);
			// future.addListener(ChannelFutureListener.CLOSE);
			System.out.println(requestData.getIntValue());
		}
		if(msg instanceof MessageData) {
			ctx.writeAndFlush(msg);
			System.out.println("Repley:" + ((MessageData)msg).getMessage());
		}
	}

}