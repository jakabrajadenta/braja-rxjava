package com.braja.rxjava.service.model;

import com.braja.rxjava.configuration.handler.IsoNettyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

import static com.braja.rxjava.util.constant.IsoConstant.*;

@Slf4j
public class IsoNettyClient {

    String connectionName;
    int port;
    String host;
    Channel channel;
    Environment environment;
    ApplicationContext applicationContext;

    protected Map<String, ChannelFuture> isoConnectionDataList;

    EventLoopGroup workGroup = new NioEventLoopGroup();

    public IsoNettyClient(String connectionName, String host, int port, Environment environment, ApplicationContext applicationContext, Map<String, ChannelFuture> isoConnectionDataList){
        this.connectionName = connectionName;
        this.host = host;
        this.port = port;
        this.environment = environment;
        this.applicationContext = applicationContext;
        this.isoConnectionDataList = isoConnectionDataList;
    }

    public void connectLoop() throws InterruptedException {
        try{
            Bootstrap b = new Bootstrap();
            b.group(workGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ChannelServerHandler(connectionName, isoConnectionDataList));
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, ISO_LENGTH_FIELD_OFFSET, ISO_LENGTH_FIELD_HEADER, ISO_LENGTH_FIELD_ADJUSTMENT, ISO_INITIAL_BYTE_STRIP));
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(ISO_LENGTH_FIELD_HEADER));
                    socketChannel.pipeline().addLast(new IsoNettyHandler(applicationContext,environment));
                }
            });
            ChannelFuture channelFuture = b.connect(this.host, this.port).sync();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            log.error("Error message : {}", e.getMessage());
            log.error("Error cause", e);
            Thread.currentThread().interrupt();
        }
        finally {
            log.debug("Finally do nothing");
        }
    }

    public void shutdown(){
        log.debug("Preparing shutdown workGroup {}:{}",this.host,this.port);
        workGroup.shutdownGracefully();
    }
}
