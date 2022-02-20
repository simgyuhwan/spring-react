package com.example.spa.service;

import com.example.spa.domain.CodeDetail;
import com.example.spa.domain.CodeDetailId;
import com.example.spa.repository.CodeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService{

    private final CodeDetailRepository repository;

    // 등록
    @Override
    public void register(CodeDetail codeDetail) throws Exception {
        String groupCode = codeDetail.getGroupCode();
        List<Object[]> rsList = repository.getMaxSortSeq(groupCode);
        Integer maxSortSeq = 0;
        if(rsList.size() > 0){
            Object[] maxVales = rsList.get(0);
            System.out.println(maxVales);

            if(maxVales != null && maxVales.length > 0){
                maxSortSeq = (Integer) maxVales[0];
            }
        }
        codeDetail.setSortSeq(maxSortSeq + 1);
        repository.save(codeDetail);
    }

    // 목록 조회
    @Override
    public List<CodeDetail> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"groupCode", "codeValue"));
    }

    // 상세 조회
    @Override
    public CodeDetail read(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = new CodeDetailId();
        codeDetailId.setGroupCode(codeDetail.getGroupCode());
        codeDetailId.setCodeValue(codeDetail.getCodeValue());
        return repository.getById(codeDetailId);
    }

    // 수정
    @Override
    public void modify(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = CodeDetailId.of(codeDetail);

        CodeDetail codeDetailEntity = repository.getById(codeDetailId);

        codeDetailEntity.setCodeValue(codeDetailId.getCodeValue());
        codeDetailEntity.setCodeName(codeDetail.getCodeName());

        repository.save(codeDetailEntity);
    }

    @Override
    public void remove(CodeDetail codeDetail) throws Exception {
        CodeDetailId codeDetailId = CodeDetailId.of(codeDetail);
        repository.deleteById(codeDetailId);
    }
}
