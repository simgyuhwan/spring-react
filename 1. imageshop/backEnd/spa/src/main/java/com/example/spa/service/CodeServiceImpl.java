package com.example.spa.service;

import com.example.spa.domain.CodeDetail;
import com.example.spa.domain.CodeGroup;
import com.example.spa.dto.CodeLabelValue;
import com.example.spa.repository.CodeDetailRepository;
import com.example.spa.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{
    private final CodeDetailRepository codeDetailRepository;
    private final CodeGroupRepository repository;

    // 코드그룹 목록 조회
    @Override
    public List<CodeLabelValue> getCodeGroupList() throws Exception {
        List<CodeGroup> codeGroups = repository.findAll(Sort.by(Sort.Direction.ASC,"groupCode"));
        List<CodeLabelValue> codeGroupList = new ArrayList<>();
        for(CodeGroup codeGroup: codeGroups){
            codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
        }
        return codeGroupList;
    }

    @Override
    public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
        List<CodeDetail> codeDetails = codeDetailRepository.getCodeList(groupCode);
        List<CodeLabelValue> codeList = new ArrayList<>();

        for(CodeDetail codeDetail : codeDetails){
            codeList.add(new CodeLabelValue(codeDetail.getCodeValue(), codeDetail.getCodeName()));
        }
        return codeList;
    }
}
