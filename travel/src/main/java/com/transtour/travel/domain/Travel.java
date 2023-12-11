package com.transtour.travel.domain;

import com.transtour.kernel.domain.travel.TravelStatus;
import com.transtour.travel.application.create.command.CreationCommand;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited(withModifiedFlag = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "travels",schema = "transtour")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Travel implements Serializable {

    @Id
    @Column(name = "travel_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_seq_gen")
    @SequenceGenerator(name = "travel_seq_gen", sequenceName = "travel_id_seq", allocationSize = 1)
    private Long orderNumber;

    @Column(name = "car_driver")
    private String carDriver;
    @Column(name = "company")
    private String company;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    @Convert(converter = TravelConverter.class)
    @Column(name = "payload", columnDefinition = "jsonb")
    private TravelInfoPayload payload;

    public static Travel create(CreationCommand command) {

        Travel travel = new Travel();
        travel.setCompany(command.getCompany());
        //travel.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC-8")).toLocalDateTime());
        travel.setStatus(TravelStatus.CREATED);
        travel.setCarDriver(command.getCarDriver());
        travel.setCreatedBy(command.getUserName());
        travel.setPayload(new TravelInfoPayload(
                command.getStatus(), ZonedDateTime.now(ZoneId.of("UTC-3")).toLocalDate(), command.getCar(), command.getCarDriver(), command.getCarDriverName(), ZonedDateTime.now().toLocalTime(),
                command.getCompany(), command.getBc(), command.getPassengerName(), command.getPassengerEmail(), command.getReserveNumber(),
                command.getOriginAddress(), command.getDestinyAddress(), command.getObservation(), command.getAmount(), command.getWhitingTime(), command.getToll(),
                command.getParkingAmount(), command.getTaxForReturn(), command.getTotalAmount(), null, null, null));
        return travel;
    }

}