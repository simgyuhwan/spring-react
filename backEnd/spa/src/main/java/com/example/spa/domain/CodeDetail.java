package com.example.spa.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"groupCode", "codeValue"})
@Entity
@IdClass(CodeDetail.class)
@Table(name="code_detail")
public class CodeDetail {

    @Id
    @Column(length = 3)
    private String groupCode;

}
