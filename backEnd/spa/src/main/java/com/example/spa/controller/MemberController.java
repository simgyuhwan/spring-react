package com.example.spa.controller;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.domain.Member;
import com.example.spa.domain.MemberAuth;
import com.example.spa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class MemberController {

    private final MemberService service;
    private final MessageSource messageSource;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Member>> list() throws Exception{
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<Member> read(@PathVariable("userNo")Long userNo) throws Exception{
        Member member = service.read(userNo);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    @PutMapping("/{userNo}")
    public ResponseEntity<Member> modify(@PathVariable("userNo")Long userNo, @Validated @RequestBody Member member) throws Exception{
        member.setUserNo(userNo);
        service.modify(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> remove(@PathVariable("userNo")Long userNo) throws Exception{
        service.remove(userNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //관리자
    @PostMapping(value = "/setup", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> setupAdmin(@Validated @RequestBody Member member) throws Exception{

        if(service.countAll()==0) {
            String inputPassword = member.getUserPw();
            member.setUserPw(passwordEncoder.encode(inputPassword));

            member.setJob("00");

            service.setupAdmin(member);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        String message = messageSource.getMessage("common.cannotSetupAdmin", null, Locale.KOREAN);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    @GetMapping("/myinfo")
    public ResponseEntity<Member> getMyInfo(@AuthenticationPrincipal CustomUser customUser) throws Exception{
        Long userNo = customUser.getUserNo();

        log.info("register userNo = " + userNo);

        Member member = service.read(userNo);

        member.setUserPw("");

        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
