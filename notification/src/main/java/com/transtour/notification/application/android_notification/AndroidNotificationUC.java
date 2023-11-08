package com.transtour.notification.application.android_notification;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.transtour.notification.application.android_notification.command.AndroidNotificationCommand;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AndroidNotificationUC {

    private static Logger logger;
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public AndroidNotificationUC(FirebaseApp firebaseApp) {
        this.firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp);
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

        String result = firebaseMessaging.send(message);
    logger.debug("Resultado de la notificacion "+result);

    }
}
