package com.braja.rxjava.service.model;

import com.braja.rxjava.configuration.handler.IsoNettyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

import static com.braja.rxjava.util.constant.IsoConstant.*;

@Slf4j
public class IsoNettyServer {

    private String connectionName;
    int port;
    Channel channel;
    Environment environment;
    ApplicationContext applicationContext;

    EventLoopGroup workGroup = new NioEventLoopGroup();

    protected Map<String, ChannelFuture> isoConnectionDataList;

    public IsoNettyServer(String connectionName, int port, Environment environment, ApplicationContext applicationContext, Map<String, ChannelFuture> isoConnectionDataList){
        this.connectionName = connectionName;
        this.port = port;
        this.environment = environment;
        this.applicationContext = applicationContext;
        this.isoConnectionDataList = isoConnectionDataList;
    }

    public void serve() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelServerHandler(connectionName, isoConnectionDataList));
                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, ISO_LENGTH_FIELD_OFFSET,ISO_LENGTH_FIELD_HEADER, ISO_LENGTH_FIELD_ADJUSTMENT, ISO_INITIAL_BYTE_STRIP));
                            socketChannel.pipeline().addLast(new LengthFieldPrepender(ISO_LENGTH_FIELD_HEADER));
                            socketChannel.pipeline().addLast(new IsoNettyHandler(applicationContext, environment));
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            log.info("IsoNettyServer::serve : Serving on port {}", port);
        } catch (Exception e) {
            log.error("Error message : connection name {} port {} {}", connectionName,port,e.getMessage());
            log.error("Error cause",e);
            shutdown();
            Thread.currentThread().interrupt();
        } finally {
            log.debug("Finally do nothing");
//            return null;
        }
    }

    public void shutdown(){
        log.debug("Preparing shutdown workGroup {}:{}","localhost",this.port);
        workGroup.shutdownGracefully();
    }
}
