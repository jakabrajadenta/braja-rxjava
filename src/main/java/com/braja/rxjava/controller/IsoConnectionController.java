package com.braja.rxjava.controller;

import com.braja.rxjava.dto.IsoConnectionDto;
import com.braja.rxjava.service.IsoConnectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/iso-connection")
public class IsoConnectionController {

    private IsoConnectionService isoConnectionService;

    @PostMapping("/connect")
    public ResponseEntity<IsoConnectionDto> connectData(@RequestBody IsoConnectionDto isoConnectionDto) throws InterruptedException {
        log.info("IsoConnectionController::connectData");
        return ResponseEntity.ok(isoConnectionService.connectData(isoConnectionDto));
    }

}
