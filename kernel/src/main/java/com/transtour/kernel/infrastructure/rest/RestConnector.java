package com.transtour.kernel.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.owasp.encoder.Encode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
public abstract class RestConnector {

    private static final String SERVICE_RESPONDED_WITH = "Service responded with [{}]";
    protected final RestConnectorConfigurationProperties properties;
    protected final RestTemplate restTemplate;

    protected RestConnector(RestConnectorConfigurationProperties properties) {
        this.properties = properties;

        var jacksonObjectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        var jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jacksonObjectMapper);
        jsonMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        var restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(15 * 1000);
        httpRequestFactory.setConnectTimeout(15 * 1000);
        httpRequestFactory.setReadTimeout(60 * 1000);

        this.restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(httpRequestFactory));

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jsonMessageConverter);
        this.restTemplate.setMessageConverters(messageConverters);

        this.restTemplate.setInterceptors(Collections.singletonList(new LoggingInterceptor()));
    }

    public RestConnectorConfigurationProperties getProperties() {
        return properties;
    }

    /**
     * Invokes a REST call
     *
     * @param <B>          the request body type
     * @param <R>          the response body type
     * @param endpoint     the endpoint is being called
     * @param httpMethod   the http method
     * @param body         the request body
     * @param clazz        the class
     * @param uriVariables the uri variables
     * @return the response body
     */
    @SneakyThrows
    public <B, R> Try<ResponseEntity<R>> invoke(String endpoint, HttpMethod httpMethod, B body, HttpHeaders headers,
                                                Class<R> clazz, Object... uriVariables) {
        return this.invoke(endpoint, () -> restTemplate.exchange(properties.getUri() + endpoint, httpMethod,
            createHttpEntity(body, headers), clazz, uriVariables));
    }

    /**
     * Invokes a REST call
     *
     * @param <B>          the request body type
     * @param <R>          the response body type
     * @param endpoint     the endpoint is being called
     * @param httpMethod   the http method
     * @param body         the request body
     * @param typezz       the typed reference
     * @param uriVariables the uri variables
     * @return the response body
     */
    @SneakyThrows
    public <B, R> Try<ResponseEntity<R>> invoke(String endpoint, HttpMethod httpMethod, B body, HttpHeaders headers,
                                                ParameterizedTypeReference<R> typezz, Object... uriVariables) {
        return this.invoke(endpoint, () -> restTemplate.exchange(properties.getUri() + endpoint, httpMethod,
            createHttpEntity(body, headers), typezz, uriVariables));
    }


    /**
     * @param endpoint the endpoint is being called
     * @param fn       the function containing the method of retrieving the response
     * @param <R>      the response body type
     * @return the response body
     */
    private <R> Try<R> invoke(String endpoint, Supplier<R> fn) {
        RetryPolicy<R> retry = new RetryPolicy<>();

        retry.handleIf(exception -> exception instanceof InterruptedException || exception instanceof IOException);

        // on failure log error
        retry.onFailure(failure -> {
            String url = properties.getUri() + endpoint;
            LOGGER.error("Failure while invoking endpoint {} {}", Encode.forJava(url),
                failure.getFailure().getMessage());

            if (failure.getFailure() instanceof HttpStatusCodeException)
                LOGGER.error(SERVICE_RESPONDED_WITH,
                    ((HttpStatusCodeException) failure.getFailure()).getResponseBodyAsString());
        });

        // maximum retries
        retry.withMaxRetries(3);

        // execute the function and envolve it on a Try
        return Try.of(() -> Failsafe.with(retry).get(fn::get));
    }

    /**
     * Handles the response body catching exceptions
     *
     * @param response the response body
     * @param <T>      the response body type
     * @return the response body
     */
    @SneakyThrows
    public <T> T handleResponse(Try<ResponseEntity<T>> response) {
        if (response.isSuccess()) {
            if (response.get().getStatusCode() != HttpStatus.OK) {
                throw new RestConnectorException(response.get().getStatusCodeValue(), (String) response.get().getBody());
            }

            return response.get().getBody();
        } else if (response.getCause() instanceof HttpClientErrorException) {
            HttpClientErrorException rawError = (HttpClientErrorException) response.getCause();

            throw new RestConnectorException(rawError.getRawStatusCode(), rawError.getResponseBodyAsString());
        } else {
            throw new RestConnectorException(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getCause().getMessage());
        }
    }

    private <B> HttpEntity<B> createHttpEntity(B body, HttpHeaders headers) {
        if (Objects.isNull(headers)) {
            headers = new HttpHeaders();
        }

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(body, headers);
    }
}
