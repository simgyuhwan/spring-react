package com.example.spa.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of="historyNo")
@ToString
@Entity
@Table(name = "pay_coin_history")
public class PayCoin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;
    private Long userNo;
    private Long itemId;

    @Transient
    private String itemName;

    private int amount;
}
