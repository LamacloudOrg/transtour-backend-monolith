package com.transtour.notification.infrastructure.listeners.guava;

import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.domain.notification.ActivacodeNotificationEmailEvent;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.notification.application.send_activation_code_mail.command.SendCodeCommand;
import org.springframework.stereotype.Service;

@Service
public class ActivationEmailCodeListener {


    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;

    public ActivationEmailCodeListener(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
    }

    @Subscribe
    public void stringEvent(ActivacodeNotificationEmailEvent event) {
        gatewayHandler.dispatch(new SendCodeCommand(
                event.getAttributes().get("to").toString()
        ));
    }
}
