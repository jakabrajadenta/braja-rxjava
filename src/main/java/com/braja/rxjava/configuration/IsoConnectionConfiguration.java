package com.braja.rxjava.configuration;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class IsoConnectionConfiguration {

    @Bean(name = "isoConnectionManager")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Map<String, ChannelFuture> isoConnectionManager( ) {
        return new ConcurrentHashMap<>();
    }
}
