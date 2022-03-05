package com.example.spa.controller;

import com.example.spa.prop.ShopProperties;
import com.example.spa.service.UserItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/useritems")
public class UserItemController {
    private final UserItemService service;
    private final ShopProperties shopProperties;


}
