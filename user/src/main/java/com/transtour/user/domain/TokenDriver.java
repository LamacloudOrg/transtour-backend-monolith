package com.transtour.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token_driver")
public class TokenDriver {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "firebase_token")
    private String firebaseToken;

    @Column(name = "device_type")
    private String deviceType;

    @OneToOne(mappedBy = "tokenDriver")
    private User user;
}

