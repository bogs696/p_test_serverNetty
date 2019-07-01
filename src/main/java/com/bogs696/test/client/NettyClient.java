package com.bogs696.test.client;

import com.bogs696.test.data.ResponseData;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static void main(String[] args) throws Exception {
		  
        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
 
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
  
                @Override
                public void initChannel(SocketChannel ch) 
                  throws Exception {
                	ChannelPipeline pipeline = ch.pipeline();
                	pipeline.addLast(new RequestDataEncoder());
                	pipeline.addLast(new ClientMessageDataEncoder());
                	pipeline.addLast(new ClientDataDecoder());
                	pipeline.addLast(new ClientHandler());
                }
                
                
            });
            
            
            ChannelFuture f = b.connect(host, port).sync();
            f.awaitUninterruptibly();
            ConsoleUI consoleUI = new ConsoleUI();
            consoleUI.start();
            //f.channel().closeFuture().sync();
        } finally {
            //workerGroup.shutdownGracefully();
        }
    }
}
