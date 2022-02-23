package com.example.spa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @LastModifiedDate
    private LocalDateTime updDate;


}
