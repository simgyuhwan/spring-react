package com.example.spa.service;

import com.example.spa.domain.Pds;

import java.util.List;

public interface PdsService {
    public void register(Pds pds) throws Exception;

    public List<Pds> list() throws Exception;
}
