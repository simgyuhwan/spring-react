package com.example.spa.service;

import com.example.spa.domain.Item;
import com.example.spa.domain.Member;
import com.example.spa.domain.PayCoin;
import com.example.spa.domain.UserItem;
import com.example.spa.repository.MemberRepository;
import com.example.spa.repository.PayCoinRepository;
import com.example.spa.repository.UserItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserItemServiceImpl implements UserItemService{
    private final UserItemRepository userItemRepository;
    private final PayCoinRepository payCoinRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void register(Member member, Item item) throws Exception {
        Long userNo = member.getUserNo();
        Long itemId = item.getItemId();
        int price = item.getPrice();

        UserItem userItem = new UserItem();
        userItem.setUserNo(userNo);
        userItem.setItemId(itemId);

        PayCoin payCoin = new PayCoin();
        payCoin.setUserNo(userNo);
        payCoin.setItemId(itemId);
        payCoin.setAmount(price);

        Member memberEntity = memberRepository.getById(userNo);

        int coin = memberEntity.getCoin();;
        int amount= payCoin.getAmount();
        memberEntity.setCoin(coin - amount);

        memberRepository.save(memberEntity);
        payCoinRepository.save(payCoin);

        userItemRepository.save(userItem);
    }

    @Override
    public List<UserItem> list(long userNo) throws Exception {
        List<Object[]> valueArrays = userItemRepository.listUserItem(userNo);
        List<UserItem> userItemList = new ArrayList<>();

        for(Object[] valueArray : valueArrays){
            UserItem userItem = new UserItem();

            userItem.setUserItemNo((Long)valueArray[0]);
            userItem.setUserNo((Long)valueArray[1]);
            userItem.setItemId((Long)valueArray[2]);
            userItem.setRegDate((LocalDateTime) valueArray[3]);
            userItem.setItemName((String)valueArray[4]);
            userItem.setPrice((int)valueArray[5]);
            userItem.setDescription((String)valueArray[6]);
            userItem.setPictureUrl((String)valueArray[7]);

            userItemList.add(userItem);
        }
        return userItemList;
    }

    @Override
    public UserItem read(Long userItemNo) throws Exception {
        List<Object[]> valueArrays = userItemRepository.readUserItem(userItemNo);

        Object[] valueArray = valueArrays.get(0);

        UserItem userItem = new UserItem();
        userItem.setUserItemNo((Long)valueArray[0]);
        userItem.setUserNo((Long)valueArray[1]);
        userItem.setItemId((Long)valueArray[2]);
        userItem.setRegDate((LocalDateTime) valueArray[3]);
        userItem.setItemName((String)valueArray[4]);
        userItem.setPrice((int)valueArray[5]);
        userItem.setDescription((String)valueArray[6]);
        userItem.setPictureUrl((String)valueArray[7]);

        return userItem;
    }
}
