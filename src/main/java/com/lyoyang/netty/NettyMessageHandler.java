package com.lyoyang.netty;

import io.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;

import java.io.UnsupportedEncodingException;

import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyMessageHandler extends SimpleChannelUpstreamHandler {
    public NettyMessageHandler() {
    }

    //响应报文最大长度16K
    private final static int response_content_length = 1024 * 16;
    private final static String CHAR_SET = "UTF-8";
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("收到请求消息开始");
        HttpRequest request = (HttpRequest) e.getMessage();
        String uri = request.getUri();
        System.out.println("请求的URI为：" + uri);
        String returnMsg = "HELLO NETTY";
        writeResponse(e, returnMsg);

    }

    private void writeResponse(MessageEvent e, String responseContent) throws UnsupportedEncodingException {
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        ChannelBuffer buffer = new DynamicChannelBuffer(response_content_length);
        buffer.writeBytes(responseContent.getBytes(CHAR_SET));
        response.setContent(buffer);
        response.setHeader("Content-Type", "text/html;charset="+CHAR_SET);
        response.setHeader("Content-Length", response.getContent().writerIndex());

        Channel ch = e.getChannel();
        try{
            // Write the initial line and the header.
            ChannelFuture channelFuture = ch.write(response);
            //只支持短连接，即写完后立即关闭连接
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }catch (Throwable throwable){
            ch.disconnect();
            ch.close();
        }
    }
}
