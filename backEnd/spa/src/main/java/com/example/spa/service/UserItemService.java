package com.example.spa.service;

import com.example.spa.domain.Item;
import com.example.spa.domain.Member;

public interface UserItemService {
    public void register(Member member, Item item)throws Exception;
}
