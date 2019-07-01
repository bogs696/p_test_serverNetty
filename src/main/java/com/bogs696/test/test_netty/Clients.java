package com.bogs696.test.test_netty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bogs696.test.data.ResponseData;

/*
 * ChannelHandlerContext
 */

import io.netty.channel.ChannelHandlerContext;
public class Clients {
	private List<ChannelHandlerContext> clients = new ArrayList<ChannelHandlerContext>();
	private static Clients instance;
	private Clients() {}
	public static Clients instance() {
		if(instance==null) {
			instance= new Clients();
		}
		return instance;
	}
	
	public void add(ChannelHandlerContext chc) {
		clients.add(chc);
		System.out.println("Clients:" + clients.size());
		if(clients.size()>1) {
			Iterator<ChannelHandlerContext> iterator = clients.iterator();
			while(iterator.hasNext()) {
				ChannelHandlerContext channelHandlerContext = iterator.next();
				if(channelHandlerContext.isRemoved()) {
					iterator.remove();
					continue;
				}
				ResponseData responseData = new ResponseData();
				responseData.setIntValue(111);
				channelHandlerContext.writeAndFlush(responseData);
			}			
		}
	}
	
	public void remove(ChannelHandlerContext chc) {
		clients.remove(chc);
		
	}
	

}