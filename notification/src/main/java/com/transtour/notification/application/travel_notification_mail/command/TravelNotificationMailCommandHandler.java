package com.transtour.notification.application.travel_notification_mail.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.notification.application.travel_notification_mail.TravelNotificationMailUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TravelNotificationMailCommandHandler implements CommandHandler<TravelNotificationMailCommand> {

    private final TravelNotificationMailUC useCase;

    public TravelNotificationMailCommandHandler(TravelNotificationMailUC useCase) {
        this.useCase = useCase;
    }

    @Override
    @SneakyThrows
    public void handle(TravelNotificationMailCommand command) {
        useCase.send(command);
    }
}
