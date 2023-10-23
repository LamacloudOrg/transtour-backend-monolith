package com.transtour.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taxi_driver")
public class Driver extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_car",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars = new HashSet<>();
    @Column(name = "firebase_token")
    private String firebaseToken;
    @Column(name = "type_device")
    private String type_device;
}

