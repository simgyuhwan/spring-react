package com.example.spa.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(length = 50, nullable = false)
    private String itemName;

    private Integer price;

    @Column(length = 20)
    private String description;

    @Column(length = 200)
    private String pictureUrl;

    @Transient
    private MultipartFile picture;

    @Transient
    private MultipartFile preview;

    @Column(length = 200)
    private String previewUrl;
}
