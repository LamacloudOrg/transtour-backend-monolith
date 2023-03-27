package com.transtour.kernel.infrastructure.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.transtour.kernel.domain.bus.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Profile("kafka")
@Component
public class KafkaPublisher {

    private final Logger logger = LoggerFactory.getLogger(KafkaPublisher.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public KafkaPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void publish(DomainEvent domainEvent, String topicName) throws KafkaProducerException, JsonProcessingException {

        ListenableFuture<SendResult<String, String>> result = this.kafkaTemplate.send(topicName, mapper.writeValueAsString(domainEvent));
        result.addCallback(new ListenableFutureCallback() {
            @Override
            public void onSuccess(Object o) {
                logger.info(String.format("Mensaje de Kafka enviado correctamente: %n %s %n", o.toString()));
            }

//      @Override
//      public void onSuccess(SendResult<String, String> stringKafkaBeanSendResult) {
//        logger.info(String.format("Mensaje de Kafka enviado correctamente: %n %s %n", stringKafkaBeanSendResult.toString()));
//      }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error(String.format("Error al enviar el mensaje de Kafka: %n %s %n", throwable.getMessage()));
            }
        });
    }
}
