package com.braja.rxjava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsoConnectionDto {

    private String ip;
    private String port;
    private String clientServer;
    private String connectionName;

    private String responseCode;
    private String responseMessage;
}
