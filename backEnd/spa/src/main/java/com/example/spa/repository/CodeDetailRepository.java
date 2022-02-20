package com.example.spa.repository;

import com.example.spa.domain.CodeDetail;
import com.example.spa.domain.CodeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {

    @Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode = ?1")
    public List<Object[]> getMaxSortSeq(String groupCode);

    @Query("SELECT cd FROM CodeDetail cd WHERE cd.groupCode = ?1")
    public List<CodeDetail> getCodeList(String groupCode);
}
