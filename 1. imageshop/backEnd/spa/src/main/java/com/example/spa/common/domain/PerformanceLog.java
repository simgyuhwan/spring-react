package com.example.spa.common.domain;

import com.example.spa.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "logNo")
@ToString
@Entity
@Table(name = "performance_log")
public class PerformanceLog extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNo;

    @Column(length = 50, nullable = false)
    private String signatureName;

    @Column(length = 100, nullable = false)
    private String signatureTypeName;

    private Long durationTime;
}
