package com.transtour.notification.infrastructure.listeners.guava;

import com.google.common.eventbus.Subscribe;
import com.transtour.kernel.domain.notification.AndroidPushNotificationEvent;
import com.transtour.kernel.domain.notification.NotificationEmailEvent;
import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.kernel.infrastructure.bus.IGatewayHandler;
import com.transtour.kernel.infrastructure.event.guava.GuavaBus;
import com.transtour.notification.application.android_notification.command.AndroidNotificationCommand;
import com.transtour.notification.application.registration_mail.command.RegistrationMailCommand;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class AndroidNotificationListener {

    private final GuavaBus eventBus;
    private final IGatewayHandler gatewayHandler;


    public AndroidNotificationListener(GuavaBus eventBus, IGatewayHandler gatewayHandler) {
        this.eventBus = eventBus;
        this.eventBus.getInternalBus().register(this);
        this.gatewayHandler = gatewayHandler;
    }

    @Subscribe
    public void stringEvent(AndroidPushNotificationEvent event) {
        gatewayHandler.dispatch(new AndroidNotificationCommand(
                event.getAttributes().get("to").toString(),
                UUID.fromString(event.getAttributes().get("notificationId").toString()),
                Long.valueOf(event.getAttributes().get("travelId").toString()),
                (TravelStatus) event.getAttributes().get("status"),
                (String)  event.getAttributes().get("origin"),
                (String) event.getAttributes().get("destiny"),
                (String) event.getAttributes().get("passenger"),
                Optional.ofNullable((String) event.getAttributes().get("observation")),
                (LocalDate) event.getAttributes().get("date"),
                (LocalTime) event.getAttributes().get("time"),
                (String) event.getAttributes().get("company"),
                (String) event.getAttributes().get("carDriver"),
                Optional.ofNullable(Double.valueOf(event.getAttributes().get("waitingTime").toString())),
                Optional.ofNullable(Double.valueOf(event.getAttributes().get("parkingAmount").toString())),
                Optional.ofNullable(Double.valueOf(event.getAttributes().get("takForReturn").toString())),
                Optional.ofNullable(Double.valueOf(event.getAttributes().get("toll").toString()))
        ));
    }


}
