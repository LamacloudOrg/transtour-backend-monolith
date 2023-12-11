package com.transtour.travel.application.change_status.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class ChangeStatusCommand implements Command {

    private final String travelId;

    public ChangeStatusCommand(String travelId) {
        this.travelId = travelId;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
