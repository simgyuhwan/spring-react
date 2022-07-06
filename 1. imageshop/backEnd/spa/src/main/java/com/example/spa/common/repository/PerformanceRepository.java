package com.example.spa.common.repository;

import com.example.spa.common.domain.PerformanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<PerformanceLog, Long> {
}
