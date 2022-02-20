package com.example.spa.service;

import com.example.spa.domain.Member;
import com.example.spa.domain.MemberAuth;
import com.example.spa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    @Override
    public void register(Member member) throws Exception {
        Member memberEntity = new Member();
        memberEntity.setUserId(member.getUserId());
        memberEntity.setUserPw(member.getUserPw());
        memberEntity.setUserName(member.getUserName());
        memberEntity.setJob(member.getJob());

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAuth("ROLE_MEMBER");

        memberEntity.addAuth(memberAuth);
        repository.save(memberEntity);
        member.setUserNo(memberEntity.getUserNo());
    }

    @Override
    public List<Member> list() throws Exception {
        List<Object[]> valueArrays = repository.listAllMember().orElseThrow(EntityNotFoundException::new);

        List<Member> memberList = new ArrayList<>();
        for(Object[] valueArray : valueArrays){
            Member member = new Member();
            member.setUserNo((Long) valueArray[0]);
            member.setUserId((String) valueArray[1]);
            member.setUserPw((String) valueArray[2]);
            member.setUserName((String) valueArray[3]);
            member.setJob((String) valueArray[4]);
            member.setCoin((int) valueArray[5]);
            member.setRegDate((LocalDateTime) valueArray[6]);
            memberList.add(member);
        }
        return memberList;
    }

    @Override
    public Member read(Long userNo) throws Exception {
        return repository.getById(userNo);
    }

    @Override
    public void modify(Member member) throws Exception {
        Member memberEntity = repository.getById(member.getUserNo());
        memberEntity.setUserName(member.getUserName());
        memberEntity.setJob(member.getJob());

        List<MemberAuth> memberAuthList = memberEntity.getAuthList();
        List<MemberAuth> authList = member.getAuthList();
        for(int i=0; i<authList.size(); i++){
            MemberAuth auth = authList.get(i);

            if(i< memberAuthList.size()){
                MemberAuth memberAuth = memberAuthList.get(i);
                memberAuth.setAuth(auth.getAuth());
            }
        }
        repository.save(memberEntity);
    }

    @Override
    public void remove(Long userNo) throws Exception {
        repository.deleteById(userNo);
    }

    @Override
    public void setupAdmin(Member member) throws Exception {
        Member memberEntity = new Member();
        memberEntity.setUserId(member.getUserId());
        memberEntity.setUserPw(member.getUserPw());
        memberEntity.setUserName(member.getUserName());
        memberEntity.setJob(member.getJob());

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAuth("ROLE_ADMIN");

        memberEntity.addAuth(memberAuth);
        repository.save(memberEntity);
    }

    @Override
    public long countAll() throws Exception {
        return repository.count();
    }
}
