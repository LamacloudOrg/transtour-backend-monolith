package com.transtour.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "firebase_token",schema = "transtour")
public class FirebaseToken implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "token")
    private String token;

    @Column(name = "device_type")
    private String deviceType;

    @OneToOne(mappedBy = "firebaseToken")
    private User user;
}

