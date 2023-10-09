package com.transtour.notification.application.android_notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.transtour.notification.application.android_notification.command.AndroidNotificationCommand;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
    public class AndroidNotificationUC {

    private final FirebaseMessaging firebaseMessaging;

    public AndroidNotificationUC(FirebaseMessaging firebaseMessaging){
        this.firebaseMessaging = firebaseMessaging;
    }

    @SneakyThrows
    public void send(AndroidNotificationCommand androidNotificationCommand) throws FirebaseMessagingException {
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

        firebaseMessaging.send(message);

    }
}
