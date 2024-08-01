package com.braja.rxjava.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class RouteRestService {

    private WebClient webClient;

    public String sendRequestIsoMessage(String isoMessageStr){
        var mapHeaders = new HashMap<String,String>();
        mapHeaders.put("X-TIMESTAMP", LocalDateTime.now().toString());
        var responseEntityStr = webClient.post().uri("http://localhost:3000/transaction/iso-raw")
                .headers(httpHeaders -> httpHeaders.setAll(mapHeaders))
                .bodyValue(isoMessageStr)
                .exchangeToMono(clientResponse -> {
                    log.info("WebClient Original Response Status Code {}", clientResponse.statusCode().value());
                    if (clientResponse.statusCode().is5xxServerError()) {
                        log.error("WebClient Error ResponseBody {}", clientResponse.toEntity(String.class));
                    }
                    return clientResponse.toEntity(String.class);
                })
//                    .onErrorReturn(e -> e.getCause() instanceof TimeoutException, handleWebClientException(requestDto, ResponseCodeMap.InputCode.SUSPEND.name())) // Response timeout
//                    .onErrorReturn(e -> e.getCause() instanceof ConnectException, handleWebClientException(requestDto, ResponseCodeMap.InputCode.TIMEOUT.name())) // Connection timeout
//                    .onErrorReturn(e -> e instanceof UnsupportedMediaTypeException, handleWebClientException(requestDto, ResponseCodeMap.InputCode.EXTERNAL_ERROR.name())) // Response body not JSON of OnlinePaymentResponseDto
//                    .onErrorReturn(e -> e instanceof CodecException, handleWebClientException(requestDto, ResponseCodeMap.InputCode.JOURNEY_NOT_FOUND.name())) // Response body not JSON of OnlinePaymentResponseDto cause 404 not found response
                .block();
        assert responseEntityStr != null;
        return responseEntityStr.getBody();
    }
}
