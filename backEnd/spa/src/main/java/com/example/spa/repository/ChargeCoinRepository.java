package com.example.spa.repository;

import com.example.spa.domain.ChargeCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeCoinRepository extends JpaRepository<ChargeCoin, Long> {
}
