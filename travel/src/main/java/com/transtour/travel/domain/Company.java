package com.transtour.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "company_id", unique = true)
    private String id;
    private String fullName;
    private String nickName;
    private String cuit;
    private String email;
    private String phone;
    private Double whitingTimeAmount;
    private Double dispositionTimeAmount;
}
