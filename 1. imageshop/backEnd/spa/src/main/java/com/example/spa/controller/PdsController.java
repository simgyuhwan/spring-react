package com.example.spa.controller;

import com.example.spa.domain.Pds;
import com.example.spa.prop.ShopProperties;
import com.example.spa.service.PdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pds")
@RequiredArgsConstructor
public class PdsController {

    private final PdsService pdsService;
    private final ShopProperties shopProperties;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Pds> register(@Validated @RequestBody Pds pds) throws Exception{
        pdsService.register(pds);

        log.info("register pds.getItemId()" + pds.getItemId());

        return new ResponseEntity<>(pds, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pds>> list() throws Exception{
        return new ResponseEntity<List<Pds>>(pdsService.list(), HttpStatus.OK);
    }
}
