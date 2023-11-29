package com.transtour.travel.infrastructure.listener.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.travel.application.generate_voucher.command.GenerateVoucherCommand;
import com.transtour.travel.domain.TravelSignedEvent;
import org.springframework.stereotype.Component;

@Component
public class TravelApproveListener {

    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;


    public TravelApproveListener(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
    }

    @Subscribe
    public void stringEvent(TravelSignedEvent event) {
        gatewayHandler.dispatch(new GenerateVoucherCommand
                ((Long) event.getAttributes().get("travelId"))
        );


    }

}
