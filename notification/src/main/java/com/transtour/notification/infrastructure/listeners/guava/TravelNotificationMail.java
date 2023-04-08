package com.transtour.notification.infrastructure.listeners.guava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.domain.notification.NotificationTravelEmailEvent;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.notification.application.travel_notification_mail.command.TravelNotificationMailCommand;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Primary
public class TravelNotificationMail {
    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;

    private final ObjectMapper mapper;

    public TravelNotificationMail(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
        this.mapper = new ObjectMapper();
    }

    @Subscribe
    public void stringEvent(NotificationTravelEmailEvent event) throws JsonProcessingException {

        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };

        HashMap<String, String> attributes = mapper.readValue(event.getAttributes().get("others").toString(), typeRef);

        gatewayHandler.dispatch(
                new TravelNotificationMailCommand(
                        event.getAttributes().get("to").toString(),
                        event.getAttributes().get("subject").toString(),
                        event.getAttributes().get("body").toString(),
                        attributes
                )
        );
    }
}
