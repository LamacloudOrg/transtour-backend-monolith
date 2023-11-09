package com.transtour.travel.application.save_taxes.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.save_taxes.SaveTaxesUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class TravelSaveTaxesCommandHandler implements CommandHandler<TravelSaveTaxesCommand> {

    private final SaveTaxesUC useCase;

    public TravelSaveTaxesCommandHandler(SaveTaxesUC useCase) {
        this.useCase = useCase;
    }

    @Override
    @SneakyThrows
    public void handle(TravelSaveTaxesCommand command) {
        useCase.save(command);
    }
}
