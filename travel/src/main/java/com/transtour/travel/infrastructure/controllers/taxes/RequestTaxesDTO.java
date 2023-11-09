package com.transtour.travel.infrastructure.controllers.taxes;

import lombok.Data;

@Data
public class RequestTaxesDTO {

    String whitingTime;
    String toll;
    String parkingAmount;
    String taxForReturn;
}
