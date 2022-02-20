package com.example.spa.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"groupCode", "codeValue"})
@ToString
public class CodeDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupCode;
    private String codeValue;

    public static CodeDetailId of(CodeDetail codeDetail){
       return CodeDetailId.builder()
                .groupCode(codeDetail.getGroupCode())
                .codeValue(codeDetail.getCodeValue())
                .build();
    }
}
