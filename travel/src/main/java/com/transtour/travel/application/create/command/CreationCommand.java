package com.transtour.travel.application.create.command;

import com.transtour.kernel.domain.bus.command.Command;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
@Data
public class CreationCommand implements Command {
    private String userName;
    private String status;
    private String car;
    private String carDriver; // Aca va el dni
    private String carDriverName;  // Aca va el nombre del chofer
    private LocalTime time;
    private String company;
    private String bc;
    private String passengerName;
    private String passengerEmail;
    private String reserveNumber;
    private String originAddress;
    private String destinyAddress;
    private String observation;
    private String amount;
    private String whitingTime;
    private String toll;
    private String parkingAmount;
    private String taxForReturn;
    private String totalAmount;

    public CreationCommand(String userName,String status, String car, String carDriver, String carDriverName, LocalTime time, String company, String bc, String passengerName, String passengerEmail, String reserveNumber, String originAddress, String destinyAddress, String observation, String amount, String whitingTime, String toll, String parkingAmount, String taxForReturn, String totalAmount) {
        this.userName = userName;
        this.status = status;
        this.car = car;
        this.carDriver = carDriver;
        this.carDriverName = carDriverName;
        this.time = time;
        this.company = company;
        this.bc = bc;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.reserveNumber = reserveNumber;
        this.originAddress = originAddress;
        this.destinyAddress = destinyAddress;
        this.observation = observation;
        this.amount = amount;
        this.whitingTime = whitingTime;
        this.toll = toll;
        this.parkingAmount = parkingAmount;
        this.taxForReturn = taxForReturn;
        this.totalAmount = totalAmount;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }
}
