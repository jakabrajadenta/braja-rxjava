package com.braja.rxjava.controller;

import com.braja.rxjava.dto.EchoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping("/echo")
    public ResponseEntity<EchoDto> echoMessage(){
        return ResponseEntity.ok(EchoDto.builder().responseCode("00").responseMessage("Success").build());
    }
}
