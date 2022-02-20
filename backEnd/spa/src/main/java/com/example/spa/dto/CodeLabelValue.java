package com.example.spa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeLabelValue {

    private String value;
    private String label;

    public CodeLabelValue(){
        super();
    }

    public CodeLabelValue(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
