package com.example.spa.common.service;

import com.example.spa.common.domain.AccessLog;
import com.example.spa.common.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccessLogServiceImpl implements AccessLogService{

    private final AccessLogRepository repository;

    @Override
    public void register(AccessLog accessLog) throws Exception {
        repository.save(accessLog);
    }

    @Override
    public List<AccessLog> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"logNo"));
    }
}
