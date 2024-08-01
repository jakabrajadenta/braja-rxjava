package com.braja.rxjava.configuration.handler;

import com.braja.rxjava.thread.ProcessIsoThread;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
public class IsoNettyHandler extends ChannelInboundHandlerAdapter {

    ApplicationContext applicationContext;
    Environment environment;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(Charset.defaultCharset());
        log.info("Received Message at {} -> {}", LocalDateTime.now(),message);
        var thread = new ProcessIsoThread(applicationContext,environment,message,ctx);
        thread.start();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

}
