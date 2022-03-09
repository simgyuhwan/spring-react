package com.example.spa.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "userItemNo")
@ToString
@Entity
@Table(name = "user_item")
public class UserItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userItemNo;
    private Long userNo;
    private Long itemId;

    @Transient
    private String itemName;

    @Transient
    private Integer price;

    @Transient
    private String description;

    @Transient
    private String pictureUrl;

}

