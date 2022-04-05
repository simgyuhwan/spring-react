package com.example.spa.common.domain;

import com.example.spa.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of="logNo")
@Entity
@Table(name = "access_log")
public class AccessLog extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNo;

    @Column(length = 200, nullable = false)
    private String requestUri;

    @Column(length = 100, nullable = false)
    private String className;

    @Column(length = 50, nullable = false)
    private String classSimpleName;

    @Column(length = 100, nullable = false)
    private String methodName;

    @Column(length = 50, nullable = false)
    private String remoteAddr;


}
