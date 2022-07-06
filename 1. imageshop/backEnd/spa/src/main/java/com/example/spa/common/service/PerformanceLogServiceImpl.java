package com.example.spa.common.service;

import com.example.spa.common.domain.PerformanceLog;
import com.example.spa.common.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerformanceLogServiceImpl implements PerformanceLogService{
    private final PerformanceRepository repository;

    @Override
    public void register(PerformanceLog performanceLog) throws Exception {
        repository.save(performanceLog);
    }

    @Override
    public List<PerformanceLog> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"logNo"));
    }
}
