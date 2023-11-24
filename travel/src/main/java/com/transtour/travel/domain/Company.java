package com.transtour.travel.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company",schema = "transtour")
public class Company {
    @Id
    @Column(name = "company_id", unique = true)
    private String id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "whiting_time_amount")
    private Double whitingTimeAmount;
    @Column(name = "disposition_time_amount")
    private Double dispositionTimeAmount;
}
