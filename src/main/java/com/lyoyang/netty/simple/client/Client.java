package com.lyoyang.netty.simple.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888);
            Channel channel = future.sync().channel();
            ChannelFuture channelFuture = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                channelFuture = channel.writeAndFlush(line);
                if ("bye".equalsIgnoreCase(line)) {
                    channel.closeFuture().sync();
                    break;
                }

                if (channelFuture != null) {
                    channelFuture.sync();
                }



            }
        } finally {
            loopGroup.shutdownGracefully();
        }


    }


}
