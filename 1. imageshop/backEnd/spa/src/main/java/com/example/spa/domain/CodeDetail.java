package com.example.spa.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Setter
@Entity
@EqualsAndHashCode(of = {"groupCode", "codeValue"})
@Table(name = "code_detail")
@IdClass(CodeDetailId.class)
public class CodeDetail extends BaseEntity{

    @Id
    @Column(length = 3)
    private String groupCode;

    @Id
    @Column(length = 3)
    private String codeValue;

    @Column(length = 30, nullable = false)
    private String codeName;

    private int sortSeq;

    @Column(length = 1)
    private String useYn = "Y";

 }
