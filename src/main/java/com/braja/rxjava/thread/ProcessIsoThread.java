package com.braja.rxjava.thread;

import com.braja.rxjava.service.RouteRestService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.braja.rxjava.util.constant.IsoConstant.MTI_RESPONSE;
import static com.braja.rxjava.util.constant.IsoConstant.MTI_REQUEST;

@Slf4j
@AllArgsConstructor
@Component
@NoArgsConstructor
public class ProcessIsoThread extends Thread {

    ApplicationContext applicationContext;
    Environment environment;
    String isoMessage;
    ChannelHandlerContext handlerContext;

    Tracer tracer;

    public ProcessIsoThread(ApplicationContext applicationContext, Environment environment, String message, ChannelHandlerContext ctx) {
        this.applicationContext = applicationContext;
        this.environment = environment;
        this.isoMessage = message;
        this.handlerContext = ctx;
        this.tracer = applicationContext.getBean(Tracer.class);
    }

    @Override
    public void run() {
        Span span = tracer.nextSpan().start();
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(span.start())){
            var genericPackager = applicationContext.getBean(GenericPackager.class);
            var routeRestService = applicationContext.getBean(RouteRestService.class);

            var isoMsg = new ISOMsg();
            isoMsg.setPackager(genericPackager);
            isoMsg.unpack(isoMessage.getBytes(StandardCharsets.UTF_8));

            var mti = isoMsg.getMTI();
            if (MTI_RESPONSE.contains(mti)){
                log.info("MTI {} is response message {}", mti, isoMessage);

            }
            else if (MTI_REQUEST.contains(mti)){
                log.info("MTI {} is request message {}", mti, isoMessage);
                var isoResponseMessage = routeRestService.sendRequestIsoMessage(isoMessage);
                handlerContext.writeAndFlush(isoResponseMessage);
            }
            else {
                log.info("MTI {} can't be handle {}", mti, isoMessage);
            }
        } catch (Exception e ){
            log.error(e.getLocalizedMessage(), e);
        } finally {
            span.end();
        }

    }
}
