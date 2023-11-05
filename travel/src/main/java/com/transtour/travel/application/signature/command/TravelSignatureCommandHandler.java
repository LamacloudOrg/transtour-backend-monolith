package com.transtour.travel.application.signature.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.signature.TravelSignatureUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class TravelSignatureCommandHandler implements CommandHandler<TravelSignatureCommand> {

    private final TravelSignatureUC uc;

    public TravelSignatureCommandHandler(TravelSignatureUC uc) {
        this.uc = uc;
    }


    @Override
    @SneakyThrows
    public void handle(TravelSignatureCommand command) {
        uc.saveSignature(command);
    }
}
