package com.transtour.travel.application.download.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class VoucherDownloadCommand implements Command {

    private final Long travelId;

    public VoucherDownloadCommand(Long travelId) {
        this.travelId = travelId;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
