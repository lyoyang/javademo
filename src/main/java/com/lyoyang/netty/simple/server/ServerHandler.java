package com.lyoyang.netty.simple.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.Date;

public class ServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("Welcome to " + InetAddress.getLocalHost() + "!\r\n");
        ctx.write("the time is " + new Date() + "\r\n");
        ctx.flush();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        boolean close = false;
        String response;
        if (StringUtils.isEmpty(request)) {
            response = "please type something";
        } else if ("bye".equals(request)) {
            response = "good bye!\r\n";
            close = true;
        } else {
            response = "What are you doing?\r\n";
        }
        ChannelFuture future = ctx.write(response);
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
