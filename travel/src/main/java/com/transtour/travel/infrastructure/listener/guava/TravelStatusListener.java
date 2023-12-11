package com.transtour.travel.infrastructure.listener.guava;

import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.domain.travel.TravelStatusEvent;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.travel.application.change_status.ChangeStatusUC;
import com.transtour.travel.application.change_status.command.ChangeStatusCommand;
import org.springframework.stereotype.Component;

@Component
public class TravelStatusListener {

    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;

    public TravelStatusListener(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
    }

    @Subscribe
    public void stringEvent(TravelStatusEvent event) {
        gatewayHandler.dispatch(new ChangeStatusCommand
                ((String) event.getAttributes().get("travelId"))
        );
    }

}
