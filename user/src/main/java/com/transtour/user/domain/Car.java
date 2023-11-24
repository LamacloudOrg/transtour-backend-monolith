package com.transtour.user.domain;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars",schema = "transtour")
@Entity
public class Car implements Serializable {
    @Id
    @Column(name = "car_id")
    private String id;

    private String patent;

    private String brand;

    private String model;
    @Column(name = "release_year")
    private LocalDate releaseYear;

}
