package com.example.spa.repository;

import com.example.spa.domain.PdsFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdsFileRepository extends JpaRepository<PdsFile, Long> {

    public List<PdsFile> findByFullName(String fullName);
}
