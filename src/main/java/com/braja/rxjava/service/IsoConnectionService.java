package com.braja.rxjava.service;

import com.braja.rxjava.dto.IsoConnectionDto;
import com.braja.rxjava.service.model.IsoNettyClient;
import com.braja.rxjava.service.model.IsoNettyServer;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class IsoConnectionService {

    @Autowired
    @Qualifier("isoConnectionManager")
    private Map<String, ChannelFuture> isoConnectionManager;
    @Autowired
    private Environment environment;
    @Autowired
    private ApplicationContext applicationContext;

    public IsoConnectionDto connectData(IsoConnectionDto isoConnectionDto) throws InterruptedException {
        if (isoConnectionDto.getClientServer().equals("SERVER")) {
            var isoNettyClient = new IsoNettyClient(
                    isoConnectionDto.getConnectionName(),
                    isoConnectionDto.getIp(),
                    Integer.parseInt(isoConnectionDto.getPort()),
                    environment,applicationContext,isoConnectionManager
            );
            isoNettyClient.connectLoop();
        }

        if (isoConnectionDto.getClientServer().equals("CLIENT")) {
            var isoNettyServer = new IsoNettyServer(
                    isoConnectionDto.getConnectionName(),
                    Integer.parseInt(isoConnectionDto.getPort()),
                    environment,applicationContext,isoConnectionManager
            );
            isoNettyServer.serve();
        }

        isoConnectionDto.setResponseCode("00");
        isoConnectionDto.setResponseMessage("Success");
        return isoConnectionDto;
    }
}
