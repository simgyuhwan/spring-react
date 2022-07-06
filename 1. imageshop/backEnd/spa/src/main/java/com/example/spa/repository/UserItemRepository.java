package com.example.spa.repository;

import com.example.spa.domain.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    public List<UserItem> findByUserNo(Long userNo);

    // 사용자 구매 상품 목록
    @Query("SELECT a.userItemNo, a.userNo, a.itemId, a.regDate, b.itemName, b.price, b.description, b.pictureUrl "
    + "FROM UserItem a INNER JOIN Item b ON a.itemId = b.itemId "
    + "WHERE a.userNo = ?1 "
    + "ORDER BY a.userItemNo DESC, a.regDate DESC")
    public List<Object[]> listUserItem(Long userNo);

    // 구매 상품 보기
    @Query("SELECT a.userItemNo, a.userNo, a.itemId, a.regDate, b.itemName, b.price, b.description, b.pictureUrl "
    + "FROM UserItem a INNER JOIN Item b ON a.itemId = b.itemId "
    + "WHERE a.userItemNo = ?1")
    public List<Object[]> readUserItem(Long userItemNo);

}
