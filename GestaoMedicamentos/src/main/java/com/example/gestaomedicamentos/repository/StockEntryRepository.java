package com.example.gestaomedicamentos.repository;

import com.example.gestaomedicamentos.entity.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryRepository extends JpaRepository <StockEntry, Long> {
}
