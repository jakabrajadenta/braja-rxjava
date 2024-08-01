package com.braja.rxjava.controller;

import com.braja.rxjava.dto.EchoDto;
import com.braja.rxjava.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping("/echo")
    public ResponseEntity<EchoDto> echoMessage(){
        return ResponseEntity.ok(transactionService.echoMessage());
    }
}
