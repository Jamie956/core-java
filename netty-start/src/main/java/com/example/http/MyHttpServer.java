package com.example.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyHttpServer {
    public static void main(String[] args) {
        //parent group, only one thread
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //child group, multi threads
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //server
            ServerBootstrap bootstrap = new ServerBootstrap();
            //event defined channel, defined handler
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitializer());

            //监听端口
            ChannelFuture channelFuture = bootstrap.bind(9990).sync();
            //?
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
