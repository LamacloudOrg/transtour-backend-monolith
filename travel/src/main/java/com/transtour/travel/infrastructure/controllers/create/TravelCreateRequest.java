package com.transtour.travel.infrastructure.controllers.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateRequest {

    private String status;
   // private LocalDate dateCreated;
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
}
