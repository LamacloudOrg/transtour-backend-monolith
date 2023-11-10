package com.transtour.travel.application.generate_voucher.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.generate_voucher.GenerateVoucherUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class GenerateVoucherCommandHandler implements CommandHandler<GenerateVoucherCommand> {

    private final GenerateVoucherUC uc;

    public GenerateVoucherCommandHandler(GenerateVoucherUC uc) {
        this.uc = uc;
    }

    @Override
    @SneakyThrows
    public void handle(GenerateVoucherCommand command) {
        uc.generate(command);
    }
}
