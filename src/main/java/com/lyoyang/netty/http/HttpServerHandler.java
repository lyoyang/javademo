package com.lyoyang.netty.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.lyoyang.guava.cache.Student;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static final String FAVICON_ICO = "/favicon.ico";

    private HttpHeaders headers;
    private HttpRequest request;
    private FullHttpRequest fullRequest;



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            request = (HttpRequest) msg;
            headers = request.headers();
            String uri = request.uri();
            log.info("request uri:" + uri);
            if (FAVICON_ICO.equals(uri)) return;
            HttpMethod method = request.method();
            if (HttpMethod.GET.equals(method)) {
                QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, Charsets.UTF_8);
                Map<String, List<String>> parameters = queryStringDecoder.parameters();
                log.info("requestParam----> {}", parameters);
            } else if (HttpMethod.POST.equals(method)) {
                fullRequest = (FullHttpRequest) msg;
                dealWithContentType();
            }

            Student student = new Student();
            student.setId("12");
            student.setClassNo("05");
            student.setName("jim");
            String resp = JSONObject.toJSONString(student);
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resp.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (!keepAlive) {
                channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set("Connection", "true");
                channelHandlerContext.write(response);
            }

        } else {
            log.info("非法请求");
        }
    }

    private void dealWithContentType() {
        String contentType = headers.get("Content-Type").split(";")[0];
        if (contentType.equals("application/json")) {
            String jsonStr = fullRequest.content().toString(Charsets.UTF_8);
            log.info("requestContent--->{}", jsonStr);
        } else if (contentType.equals("application/x-www-form-urlencoded")) {
            String requestStr = fullRequest.content().toString(Charsets.UTF_8);
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(requestStr, Charsets.UTF_8);
            log.info("requestContent--->{}", queryStringDecoder);
        } else {
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("HttpServerHandler happened exception", cause);
        ctx.close();
    }
}
