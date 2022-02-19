package com.example.spa.controller;

import com.example.spa.domain.CodeGroup;
import com.example.spa.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/codegroups")
public class CodeGroupController {
    private final CodeGroupService service;

    // 등록
    @PostMapping
    public ResponseEntity<CodeGroup> register(@Validated @RequestBody CodeGroup codeGroup) throws Exception{
        log.info("register");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println(codeGroup.toString());
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("=========================");
        service.register(codeGroup);
        log.info("register codeGroup.getCodeGroupNo()= "+ codeGroup.getGroupCode());
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<CodeGroup>> list() throws Exception{
        log.info("list");
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    // 상세조회
    @GetMapping("/{groupCode}")
    public ResponseEntity<CodeGroup> read(@PathVariable("groupCode")String groupCode) throws Exception{
        CodeGroup codeGroup = service.read(groupCode);
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{groupCode}")
    public ResponseEntity<CodeGroup> modify(@PathVariable("groupCode")String groupCode, @RequestBody CodeGroup codeGroup) throws Exception{
        codeGroup.setGroupCode(groupCode);
        service.modify(codeGroup);
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{groupCode}")
    public ResponseEntity<Void> remove(@PathVariable("groupCode")String groupCode) throws Exception{
        service.remove(groupCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
