package com.example.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //分配线程数运行
//        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap boot = new ServerBootstrap();

            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //线程队列连接数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //bossGroup channel
//                    .handler(null)
                    //workerGroup 通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //接受各个客户端的channel
                            //socket channel pipeline to self-definition handler
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("Server is ready!");

            //启动服务
            ChannelFuture cf = boot.bind(6668).sync();
            //监听关闭通道
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
