package com.braja.rxjava.configuration.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import lombok.extern.slf4j.Slf4j;

import static java.nio.charset.Charset.defaultCharset;

@Slf4j
public class WebClientLogHandler extends ChannelDuplexHandler {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof FullHttpRequest request) {
            log.info("WebClient FullHttpRequest -> Method: {}, URI: {}", request.method(), request.uri());
            log.info("WebClient FullHttpRequest -> Headers: {}", request.headers());
            log.info("WebClient FullHttpRequest -> Body: {}", request.content().toString(defaultCharset()));
        } else if (msg instanceof HttpRequest request) {
            log.info("WebClient HttpRequest -> Method: {}, URI: {}", request.method(), request.uri());
            log.info("WebClient HttpRequest -> Headers: {}", request.headers());
        } else if (msg instanceof FullHttpMessage message) {
            log.info("WebClient FullHttpMessage -> Body: {}", message.content().toString(defaultCharset()));
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpResponse response) {
            log.info("WebClient FullHttpResponse -> Status: {}", response.status().code());
            log.info("WebClient FullHttpResponse -> Headers: {}", response.headers());
            log.info("WebClient FullHttpResponse -> Body: {}", response.content().toString(defaultCharset()));
        } else if (msg instanceof HttpResponse response) {
            log.info("WebClient HttpResponse -> Status: {}", response.status().code());
            log.info("WebClient HttpResponse -> Headers: {}", response.headers());
        } else if (!(msg instanceof LastHttpContent) && msg instanceof HttpContent httpContent) {
            log.info("WebClient LastHttpContent -> Body: {}", httpContent.content().toString(defaultCharset()));
        }
        super.channelRead(ctx, msg);
    }
}
