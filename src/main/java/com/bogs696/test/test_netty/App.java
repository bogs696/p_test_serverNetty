package com.bogs696.test.test_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class App 
{
	
	public App(int port) {
		super();
		this.port = port;
	}

	private int port;
	 
 
    public static void main(String[] args) throws Exception {
  
        int port = args.length > 0
          ? Integer.parseInt(args[0])
          : 8080;
  
        new App(port).run();
    }
 
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<Channel>() {
                @Override
                public void initChannel(Channel ch) 
                  throws Exception {
                    ch.pipeline().addLast(new ServerDataDecoder(), 
                      new ResponseDataEncoder(), new ServerMessageDataEncoder(),
                      new ProcessingHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128)
              .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}