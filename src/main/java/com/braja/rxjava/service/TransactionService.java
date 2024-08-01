package com.braja.rxjava.service;

import com.braja.rxjava.dto.EchoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    public EchoDto echoMessage(){
        return EchoDto.builder().responseCode("00").responseMessage("Success").build();
    }
}
