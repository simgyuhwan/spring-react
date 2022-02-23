package com.example.spa.service;

import com.example.spa.domain.Notice;
import com.example.spa.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository repository;

    @Override
    public void register(Notice notice) throws Exception {
        repository.save(notice);
    }

    @Override
    public List<Notice> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"noticeNo"));
    }

    @Override
    public Notice read(Long noticeNo) throws Exception {
        return repository.getById(noticeNo);
    }

    @Override
    public void modify(Notice notice) throws Exception {
        Notice byId = repository.getById(notice.getNoticeNo());
        byId.setContent(notice.getContent());
        byId.setTitle(notice.getTitle());
        repository.save(byId);
    }

    @Override
    public void remove(Long noticeNo) throws Exception {
        repository.deleteById(noticeNo);
    }
}
