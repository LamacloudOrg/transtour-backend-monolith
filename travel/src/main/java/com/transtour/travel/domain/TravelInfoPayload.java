package com.transtour.travel.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelInfoPayload implements Serializable {

    private String status;
    private LocalDate dateCreated;
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
    private byte[] signature;

}
