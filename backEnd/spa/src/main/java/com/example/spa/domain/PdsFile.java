package com.example.spa.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pds_file")
public class PdsFile extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pdsFileId;

    @Column(length = 20)
    private String fullName;

    private Integer downCnt = 0;

}
