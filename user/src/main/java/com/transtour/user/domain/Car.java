package com.transtour.user.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
@Entity
public class Car {
    @Id
    private String id;

    private String patent;

    private String brand;

    private String model;

    private LocalDate releaseYear;

}
