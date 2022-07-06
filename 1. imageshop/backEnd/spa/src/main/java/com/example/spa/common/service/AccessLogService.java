package com.example.spa.common.service;

import com.example.spa.common.domain.AccessLog;

import java.util.List;

public interface AccessLogService {
    public void register(AccessLog accessLog) throws Exception;
    public List<AccessLog> list() throws Exception;
}
