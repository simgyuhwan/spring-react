package com.example.spa.service;

import com.example.spa.domain.Notice;

import java.util.List;

public interface NoticeService {
    public void register(Notice notice)throws Exception;

    public List<Notice> list() throws Exception;

    public Notice read(Long noticeNo)throws Exception;

    public void modify(Notice notice)throws Exception;

    public void remove(Long noticeNo)throws Exception;
}
