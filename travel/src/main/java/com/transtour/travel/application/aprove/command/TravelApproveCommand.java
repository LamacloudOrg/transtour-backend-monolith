package com.transtour.travel.application.aprove.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class TravelApproveCommand implements Command {
    private final Long id;

    public TravelApproveCommand(Long id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
