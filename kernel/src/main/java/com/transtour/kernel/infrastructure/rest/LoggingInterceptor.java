package com.transtour.kernel.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {
        //LOGGER.debug("Request body: \n{}", new String(reqBody, StandardCharsets.UTF_8));
        ClientHttpResponse response = ex.execute(req, reqBody);
        var isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        var reader = new BufferedReader(isr);
        String body = reader.lines().collect(Collectors.joining("\n"));
        //LOGGER.debug("Response body: \n{}", body);
        reader.close();
        return response;
    }
}
