package com.example.spa.controller;

import com.example.spa.domain.CodeDetail;
import com.example.spa.dto.CodeLabelValue;
import com.example.spa.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/codes")
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/codeGroup")
    public ResponseEntity<List<CodeLabelValue>> codeGroupList() throws Exception{
        log.info("codeGroupList");
        return new ResponseEntity<>(codeService.getCodeGroupList(), HttpStatus.OK);
    }

    @GetMapping("/job")
    public ResponseEntity<List<CodeLabelValue>> jobList() throws Exception{
        log.info("jobList");

        String classCode = "A01";
        List<CodeLabelValue> jobList = codeService.getCodeList(classCode);
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

}
