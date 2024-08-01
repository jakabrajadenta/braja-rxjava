package com.braja.rxjava.service.model;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@AllArgsConstructor
public class ChannelServerHandler extends ChannelInboundHandlerAdapter {

    private String connectionName;
    protected Map<String, ChannelFuture> isoConnectionDataList;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture channelFuture = ctx.channel().closeFuture();
        log.info("Client connected. Channel ID: {}", ctx.channel().id().asLongText());
        if(!channelFuture.channel().isActive()){
            log.info("Channel not active, exiting... ");
            return;
        }
        isoConnectionDataList.put(connectionName, channelFuture);
        log.info("Added connection: {}, map: {} ", connectionName, isoConnectionDataList);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        isoConnectionDataList.remove(connectionName);
        log.info("Removed connection: {}, map : {} ", connectionName, isoConnectionDataList);
    }
}
