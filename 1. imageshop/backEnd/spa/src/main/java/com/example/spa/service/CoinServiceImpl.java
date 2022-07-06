package com.example.spa.service;

import com.example.spa.domain.ChargeCoin;
import com.example.spa.domain.Member;
import com.example.spa.repository.ChargeCoinRepository;
import com.example.spa.repository.MemberRepository;
import com.example.spa.repository.PayCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CoinServiceImpl implements CoinService{

    private final ChargeCoinRepository chargeCoinRepository;
    private final MemberRepository memberRepository;
    private final PayCoinRepository payCoinRepository;

    @Transactional
    @Override
    public void charge(ChargeCoin chargeCoin) throws Exception {
        Member memberEntity = memberRepository.getById(chargeCoin.getUserNo());

        int coin = memberEntity.getCoin();
        int amount = chargeCoin.getAmount();

        memberEntity.setCoin(coin + amount);
        memberRepository.save(memberEntity);
        chargeCoinRepository.save(chargeCoin);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChargeCoin> list(Long userNo) {
        return chargeCoinRepository.findAll(Sort.by(Sort.Direction.DESC,"historyNo"));
    }

    @Override
    public List<Object[]> listPayHistory(Long userNo) throws Exception {
        return payCoinRepository.listPayHistory(userNo);
    }
}
