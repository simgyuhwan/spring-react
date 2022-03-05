package com.example.spa.service;

import com.example.spa.domain.ChargeCoin;

import java.util.List;

public interface CoinService {
    public void charge(ChargeCoin chargeCoin)throws Exception;

    public List<ChargeCoin> list(Long userNo);
}
