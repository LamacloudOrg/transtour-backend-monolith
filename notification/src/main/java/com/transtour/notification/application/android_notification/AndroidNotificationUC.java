package com.transtour.notification.application.android_notification;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.transtour.kernel.domain.bus.DomainEvent;
import com.transtour.kernel.domain.bus.EventBus;
import com.transtour.notification.application.android_notification.command.AndroidNotificationCommand;
import com.transtour.kernel.domain.travel.TravelStatusEvent;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AndroidNotificationUC {

    private static Logger logger;
    private final FirebaseMessaging firebaseMessaging;
    private final EventBus eventBus;

    @Autowired
    public AndroidNotificationUC(FirebaseApp firebaseApp,
                                 @Qualifier("GuavaImpl") EventBus eventBus) {
        this.firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp);
        this.eventBus = eventBus;
        logger = LoggerFactory.getLogger(AndroidNotificationUC.class);
    }

    @SneakyThrows
    public void send(AndroidNotificationCommand androidNotificationCommand) throws FirebaseMessagingException {
        logger.info("Enviando Notificacion");
        Notification notification = Notification
                .builder()
                .setTitle("Se genero un nuevo viaje")
                .setBody("Probando")
                .build();

        Message message = Message
                .builder()
                .setToken(androidNotificationCommand.getTo())
                .setNotification(notification)
                .putAllData(androidNotificationCommand.getInfo())
                .build();

        try {
            String result = firebaseMessaging.send(message);
            logger.debug("Resultado de la notificacion "+result);
        } catch (FirebaseMessagingException e) {
            logger.error("Excepcion en el envio ");
            eventBus.publish(notificationEvent(androidNotificationCommand.getInfo().get("travelId")));
        }
    }

    private List<DomainEvent> notificationEvent(String travelId) {
        return Collections.singletonList(TravelStatusEvent.create(travelId));
    }
}
