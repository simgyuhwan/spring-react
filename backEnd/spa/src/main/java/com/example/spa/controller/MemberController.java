package com.example.spa.controller;

import com.example.spa.domain.Member;
import com.example.spa.domain.MemberAuth;
import com.example.spa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class MemberController {

    private final MemberService service;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Member> register(@Validated @RequestBody Member member) throws Exception{
        log.info("member.getUserName() = " + member.getUserName());

        String inputPassword = member.getUserPw();
        member.setUserPw(passwordEncoder.encode(inputPassword));

        service.register(member);

        log.info("register member.getUserNo()=" + member.getUserNo());
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Member>> list() throws Exception{
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<Member> read(@PathVariable("userNo")Long userNo) throws Exception{
        Member member = service.read(userNo);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/{userNo}")
    public ResponseEntity<Member> modify(@PathVariable("userNo")Long userNo, @Validated @RequestBody Member member) throws Exception{
        member.setUserNo(userNo);
        service.modify(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> remove(@PathVariable("userNo")Long userNo) throws Exception{
        service.remove(userNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
