package com.transtour.travel.application.download.command;

import com.transtour.kernel.domain.bus.command.CommandHandler;
import com.transtour.travel.application.download.VoucherDownloadUC;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class VoucherDownloadCommandHandler implements CommandHandler<VoucherDownloadCommand> {

    private final VoucherDownloadUC voucherDownloadUC;

    public VoucherDownloadCommandHandler(VoucherDownloadUC voucherDownloadUC) {
        this.voucherDownloadUC = voucherDownloadUC;
    }

    @Override
    @SneakyThrows
    public void handle(VoucherDownloadCommand command) {}
}
