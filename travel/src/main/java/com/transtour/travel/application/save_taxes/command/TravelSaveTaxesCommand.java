package com.transtour.travel.application.save_taxes.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class TravelSaveTaxesCommand implements Command {
    private final Long id;
    private final String whitingTime;
    private final String toll;
    private final String parkingAmount;
    private final String taxForReturn;

    public TravelSaveTaxesCommand(Long id, String whitingTime, String toll, String parkingAmount, String taxForReturn) {
        this.id = id;
        this.whitingTime = whitingTime;
        this.toll = toll;
        this.parkingAmount = parkingAmount;
        this.taxForReturn = taxForReturn;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }
}
