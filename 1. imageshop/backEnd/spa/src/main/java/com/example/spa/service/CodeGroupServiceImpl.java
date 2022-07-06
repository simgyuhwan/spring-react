package com.example.spa.service;

import com.example.spa.domain.CodeGroup;
import com.example.spa.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeGroupServiceImpl implements CodeGroupService{
    private final CodeGroupRepository repository;

    @Override
    public void register(CodeGroup codeGroup) throws Exception {
        repository.save(codeGroup);
    }

    @Override
    public List<CodeGroup> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"groupCode"));
    }

    @Override
    public CodeGroup read(String groupCode) throws Exception {
        return repository.getById(groupCode);
    }

    @Override
    public void modify(CodeGroup codeGroup) throws Exception {
        CodeGroup findCodeGroup = repository.getById(codeGroup.getGroupCode());
        findCodeGroup.setGroupName(codeGroup.getGroupName());
        repository.save(findCodeGroup);
    }

    @Override
    public void remove(String groupCode) throws Exception {
        repository.deleteById(groupCode);
    }
}
