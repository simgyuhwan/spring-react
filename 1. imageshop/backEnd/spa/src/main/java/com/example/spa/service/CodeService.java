package com.example.spa.service;

import com.example.spa.domain.CodeDetail;
import com.example.spa.dto.CodeLabelValue;

import java.util.List;

public interface CodeService {
    public List<CodeLabelValue> getCodeGroupList() throws Exception;

    public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;

}
