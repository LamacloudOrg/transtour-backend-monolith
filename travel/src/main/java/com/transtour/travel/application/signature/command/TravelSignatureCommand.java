package com.transtour.travel.application.signature.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class TravelSignatureCommand implements Command {

    private final Long travelId;
    private final String signature;
    private final int width;
    private final int hegiht;

    public TravelSignatureCommand(Long travelId, String signature, int width, int hegiht) {
        this.travelId = travelId;
        this.signature = signature;
        this.width = width;
        this.hegiht = hegiht;
    }


    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
