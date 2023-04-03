package com.transtour.travel.domain;

import com.transtour.travel.application.create.command.CreationCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travels")
public class Travel extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "travel_id_seq")
    private Long orderNumber;


    @Convert(converter = TravelConverter.class)
    @Column(columnDefinition = "jsonb")
    private TravelInfoPayload payload;

    private String carDriver; // Aca va el dni
    private String company;
    private LocalDate dateCreated;
    private String status;



    public static Travel create (CreationCommand command) {
        LocalDateTime localDateTime = LocalDateTime.now();

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC-8"));

        Travel travel = new Travel();
        travel.setCompany(command.getCompany());
        travel.setDateCreated(ZonedDateTime.now().toLocalDate());
        travel.setStatus(command.getStatus());
        travel.setCarDriver(command.getCarDriver());
        travel.setPayload(new TravelInfoPayload(
                command.getStatus(), command.getDateCreated(), command.getCar(), command.getCarDriver(), command.getCarDriverName(), ZonedDateTime.now().toLocalTime(),
                command.getCompany(), command.getBc(), command.getPassengerName(), command.getPassengerEmail(), command.getReserveNumber(),
                command.getOriginAddress(), command.getDestinyAddress(), command.getObservation(), command.getAmount(), command.getWhitingTime(), command.getToll(),
                command.getParkingAmount(), command.getTaxForReturn(), command.getTotalAmount()));
        return travel;
    }

}