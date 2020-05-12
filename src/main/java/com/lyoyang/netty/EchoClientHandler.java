package com.lyoyang.netty;

import com.lyoyang.entity.Student;
import com.lyoyang.entity.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    static final String ECHO_REQ = "Hello Netty,this is my first demo.$_";

    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.write(createStu(i));
//            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ, CharsetUtil.UTF_8));
        }
        ctx.flush();
    }

    private User createUser(int i) {
        User user = new User();
        user.setId(i);
        user.setName("user:" + i);
        user.setAge(i+5);
        return user;
    }

    private Student createStu(int i) {
        Student student = new Student();
        student.setId(i);
        student.setUsername("stu:" + i);
        return student;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("the client receive:" + msg + "--->" + (++counter));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
