package com.example.spa.common.service;

import com.example.spa.common.domain.PerformanceLog;

import java.util.List;

public interface PerformanceLogService {
    public void register(PerformanceLog performanceLog) throws Exception;
    public List<PerformanceLog> list() throws Exception;
}
