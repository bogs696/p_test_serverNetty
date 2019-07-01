package com.bogs696.test.client;

import io.netty.channel.ChannelHandlerContext;

public class ServerHelper {
	private static ServerHelper instance;
	private ChannelHandlerContext channelHandlerContext;
	private ServerHelper() {}
	public static synchronized ServerHelper instance() {
		if(instance==null) {
			instance = new ServerHelper();
		}
		return instance;
	}
	public ChannelHandlerContext getChannelHandlerContext() {
		return channelHandlerContext;
	}
	public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
		this.channelHandlerContext = channelHandlerContext;
	}
	
	
}
