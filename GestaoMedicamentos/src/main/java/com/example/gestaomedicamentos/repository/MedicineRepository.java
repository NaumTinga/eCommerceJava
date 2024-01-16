package com.example.gestaomedicamentos.repository;

import com.example.gestaomedicamentos.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository <Medicine, Long> {
}
