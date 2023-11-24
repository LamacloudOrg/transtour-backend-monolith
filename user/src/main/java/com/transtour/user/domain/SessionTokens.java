package com.transtour.user.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session_tokens",schema = "transtour")
public class SessionTokens implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(unique = true)
    private String token;
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private TokenType tokenType; //= TokenType.BEARER;
    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

}