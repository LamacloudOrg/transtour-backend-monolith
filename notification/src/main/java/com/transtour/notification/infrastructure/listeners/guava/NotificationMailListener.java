package com.transtour.notification.infrastructure.listeners.guava;

import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.domain.notification.NotificationEmailEvent;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.notification.application.registration_mail.command.RegistrationMailCommand;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NotificationMailListener {

    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;

    public NotificationMailListener(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
    }

    @Subscribe
    public void stringEvent(NotificationEmailEvent event) {
        gatewayHandler.dispatch(new RegistrationMailCommand(
                event.getAttributes().get("from").toString(),
                event.getAttributes().get("to").toString(),
                event.getAttributes().get("subject").toString(),
                event.getAttributes().get("body").toString()));
    }
}
