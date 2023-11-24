package com.transtour.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles",schema = "transtour")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id", nullable = false)
    private String id;

    @Column(nullable = false, length = 45)
    private String name;

}