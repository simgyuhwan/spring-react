package com.example.spa.controller;

import com.example.spa.domain.CodeDetail;
import com.example.spa.domain.CodeDetailId;
import com.example.spa.service.CodeDetailService;
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
@RequestMapping("/codedetails")
public class CodeDetailController {

    private final CodeDetailService codeDetailService;

    @PostMapping
    public ResponseEntity<CodeDetail> register(@Validated @RequestBody CodeDetail codeDetail) throws Exception{
        log.info("register");

        codeDetailService.register(codeDetail);

        log.info("register codeDetail.getCodeClassNo() = " + codeDetail.getGroupCode());
        log.info("register codeDetail.getCodeValue() = " + codeDetail.getCodeValue());

        return new ResponseEntity<>(codeDetail, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CodeDetail>> list() throws Exception{
        log.info("list");
        return new ResponseEntity<>(codeDetailService.list(), HttpStatus.OK);

    }

    @GetMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<CodeDetail> read(@PathVariable("groupCode") String groupCode, @PathVariable("codeValue")String codeValue)throws Exception{
        CodeDetail codeDetail = new CodeDetail();
        codeDetail.setGroupCode(groupCode);
        codeDetail.setCodeValue(codeValue);

        return new ResponseEntity<>(codeDetailService.read(codeDetail), HttpStatus.OK);
    }

    @PutMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<CodeDetail> modify(@PathVariable("groupCode")String groupCode, @PathVariable("codeValue")String codeValue,
                                             @Validated @RequestBody CodeDetail codeDetail) throws Exception{
        codeDetail.setGroupCode(groupCode);
        codeDetail.setCodeValue(codeValue);

        codeDetailService.modify(codeDetail);
        return new ResponseEntity<>(codeDetail, HttpStatus.OK);
    }

    @DeleteMapping("/{groupCode}/{codeValue}")
    public ResponseEntity<Void> remove(@PathVariable("groupCode")String groupCode, @PathVariable("codeValue")String codeValue) throws Exception{
        CodeDetail codeDetail = new CodeDetail();
        codeDetail.setCodeValue(codeValue);
        codeDetail.setGroupCode(groupCode);

        codeDetailService.remove(codeDetail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
