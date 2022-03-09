package com.example.spa.service;

import com.example.spa.domain.Item;
import com.example.spa.domain.Member;
import com.example.spa.domain.UserItem;

import java.util.List;

public interface UserItemService {
    public void register(Member member, Item item)throws Exception;

    public List<UserItem> list(long userNo) throws Exception;

    public UserItem read(Long userItemNo) throws Exception;
}
