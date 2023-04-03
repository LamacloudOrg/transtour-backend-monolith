package com.transtour.travel.domain;

import com.transtour.travel.application.create.command.CreationCommand;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travel")


@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})


public class Travel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderNumber;


    @Convert(converter = TravelConverter.class)
    @Column(columnDefinition = "jsonb")
    private TravelInfoPayload payload;

    private String carDriver; // Aca va el dni
    private String company;
    private LocalDate dateCreated;
    private String status;



    public static Travel create (CreationCommand command){
        Travel travel = new Travel();
        travel.setCompany(command.getCompany());
        travel.setDateCreated(command.getDateCreated());
        travel.setStatus(command.getStatus());
        travel.setCarDriver(command.getCarDriver());
        travel.setPayload(new TravelInfoPayload(
                command.getStatus(), command.getDateCreated(), command.getCar(), command.getCarDriver(), command.getCarDriverName(), command.getTime(),
                command.getCompany(), command.getBc(), command.getPassengerName(), command.getPassengerEmail(), command.getReserveNumber(),
                command.getOriginAddress(), command.getDestinyAddress(), command.getObservation(), command.getAmount(), command.getWhitingTime(), command.getToll(),
                command.getParkingAmount(), command.getTaxForReturn(), command.getTotalAmount()));
        return travel;
    }

}