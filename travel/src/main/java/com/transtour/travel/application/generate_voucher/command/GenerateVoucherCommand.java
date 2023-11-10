package com.transtour.travel.application.generate_voucher.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class GenerateVoucherCommand implements Command {

    private final Long travelId;

    public GenerateVoucherCommand(Long travelId) {
        this.travelId = travelId;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
