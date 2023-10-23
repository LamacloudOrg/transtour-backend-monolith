package com.transtour.notification.application.android_notification.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.notification.application.android_notification.AndroidNotificationUC;
import com.transtour.notification.application.registration_mail.RegistrationMailUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class AndroidNotificationCommandHandler implements CommandHandler<AndroidNotificationCommand> {
    private final AndroidNotificationUC useCase;

    public AndroidNotificationCommandHandler(AndroidNotificationUC useCase) {
        this.useCase = useCase;
    }

    @SneakyThrows
    @Override
    public void handle(AndroidNotificationCommand command) {
        useCase.send(command);
    }
}
