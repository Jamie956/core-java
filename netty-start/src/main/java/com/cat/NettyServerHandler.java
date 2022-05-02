package com.cat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 自定义 handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端发送的消息
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //debug ctx
//        System.out.println("Server read Thread: " + Thread.currentThread().getName());
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("Receive client message: " + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("Client address: " + ctx.channel().remoteAddress());
//    }

    /**
     * 提交到NIOEventLoop 的任务队列 taskQueue，非阻塞
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //加入任务队列，非阻塞
//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10 * 1000);
//                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hi client", CharsetUtil.UTF_8));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * 用户自定义定时任务 -> 该任务提交到 scheduleTaskQueue
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hi client", CharsetUtil.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);
        //ctx.pipeline.channel.eventLoop.scheduleTaskQueue
        System.out.println("debug");
    }

    /**
     * 数据读取完毕
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hi client~", CharsetUtil.UTF_8));
        System.out.println("Finished");
    }

    /**
     * 处理异常，一般需要关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
