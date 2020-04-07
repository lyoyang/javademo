package com.lyoyang.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

//    private final ByteBuf reqMessage;

    private byte[] reqBytes;

    public TimeClientHandler() {
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        reqMessage = Unpooled.buffer(req.length);
//        reqMessage.writeBytes(req);
        reqBytes = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = null;
        for (int i = 0; i < 100; i++) {
            byteBuf = Unpooled.buffer(reqBytes.length);
            byteBuf.writeBytes(reqBytes);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
        String respMsg = (String) msg;
//        byte[] respBytes = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(respBytes);
//        String respContent = new String(respBytes, StandardCharsets.UTF_8);
        System.out.println("receive server resp:" + respMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
