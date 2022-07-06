package com.example.spa.repository;

import com.example.spa.domain.PayCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayCoinRepository extends JpaRepository<PayCoin, Long> {
    // 사용자의 상품 구매 내역을 반환한다.
    @Query("SELECT a.historyNo, a.userNo, a.itemId, b.itemName, a.amount, a.regDate "
    + "FROM PayCoin a INNER JOIN Item b ON a.itemId = b.itemId "
    + "WHERE a.historyNo > 0 AND a.userNo = ?1 "
    + "ORDER BY a.historyNo DESC")
    public List<Object[]> listPayHistory(Long userNo);

}
