package com.example.spa.controller;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.domain.ChargeCoin;
import com.example.spa.domain.PayCoin;
import com.example.spa.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coins")
public class CoinController {

    private final CoinService service;
    private final MessageSource messageSource;

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping(value = "/charge/{amount}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> charge(@PathVariable("amount")int amount, @AuthenticationPrincipal CustomUser customUser)throws Exception{
        long userNo = customUser.getUserNo();

        ChargeCoin chargeCoin = new ChargeCoin();

        chargeCoin.setUserNo(userNo);
        chargeCoin.setAmount(amount);

        service.charge(chargeCoin);

        String message = messageSource.getMessage("coin.chargingComplete", null, Locale.KOREAN);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping
    public ResponseEntity<List<ChargeCoin>> list(@AuthenticationPrincipal CustomUser customUser)throws Exception{
        Long userNo = customUser.getUserNo();
        return new ResponseEntity<>(service.list(userNo), HttpStatus.OK);
    }

    @GetMapping("/pay")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<List<PayCoin>> listPayHistory(@AuthenticationPrincipal CustomUser customUser) throws Exception{
        Long userNo = customUser.getUserNo();

        return new ResponseEntity<>(service.listPayHistory(userNo), HttpStatus.OK);
    }
}
