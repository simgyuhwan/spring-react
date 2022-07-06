package com.example.spa.controller;

import com.example.spa.domain.Notice;
import com.example.spa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {
    private final NoticeService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Notice> register(@Validated @RequestBody Notice notice) throws Exception{
        service.register(notice);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Notice>> list() throws Exception{
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<Notice> read(@PathVariable("noticeNo")Long noticeNo) throws Exception{
        Notice notice = service.read(noticeNo);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{noticeNo}")
    public ResponseEntity<Notice> modify(@PathVariable("noticeNo") Long noticeNo, @Validated @RequestBody Notice notice) throws Exception{
        notice.setNoticeNo(noticeNo);
        service.modify(notice);

        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> remove(@PathVariable("noticeNo")Long noticeNo) throws Exception{
        service.remove(noticeNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
