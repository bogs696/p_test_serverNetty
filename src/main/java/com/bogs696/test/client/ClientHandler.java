package com.bogs696.test.client;

import com.bogs696.test.data.MessageData;
import com.bogs696.test.data.RequestData;
import com.bogs696.test.data.ResponseData;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	private int message;
	public ClientHandler() {
		message=123;
	}
	public ClientHandler(int message) {
		this.message = message;
	}
    @Override
    public void channelActive(ChannelHandlerContext ctx) 
      throws Exception {
    	ServerHelper.instance().setChannelHandlerContext(ctx);
        RequestData msg = new RequestData();
        msg.setIntValue(message);
        msg.setStringValue(
          "all work and no play makes jack a dull boy");
        //ChannelFuture future = ctx.writeAndFlush(msg);
        ctx.writeAndFlush(new MessageData("Test"));
        ctx.flush();
        System.out.println("client flush");
    }
 
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) 
      throws Exception {
    	if (msg instanceof ResponseData) {
            System.out.println(((ResponseData)msg).getIntValue());			
		}
    	if(msg instanceof MessageData) {
    		System.out.println(((MessageData)msg).getMessage());
    	}
        //ctx.close();
    }

}
