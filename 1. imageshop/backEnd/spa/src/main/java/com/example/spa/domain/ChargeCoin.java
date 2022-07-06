package com.example.spa.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@EqualsAndHashCode(of="historyNo")
@ToString
@Getter
@Table(name = "charge_coin_history")
public class ChargeCoin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;
    private Long userNo;
    private int amount;

}
