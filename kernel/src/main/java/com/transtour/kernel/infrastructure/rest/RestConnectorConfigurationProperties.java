package com.transtour.kernel.infrastructure.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RestConnectorConfigurationProperties {
    private String uri;
}
