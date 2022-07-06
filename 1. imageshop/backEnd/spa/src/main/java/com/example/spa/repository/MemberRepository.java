package com.example.spa.repository;

import com.example.spa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m.userNo, m.userId, m.userPw, m.userName, cd.codeName, m.coin, m.regDate "
    + "FROM Member m "
    + "INNER JOIN CodeDetail cd ON cd.codeValue = m.job "
    + "INNER JOIN CodeGroup cg ON cg.groupCode = cd.groupCode "
    + "WHERE cg.groupCode = 'A01' ORDER BY m.regDate DESC")
    public Optional<List<Object[]>> listAllMember();

    public Member findByUserId(String userId);
}
