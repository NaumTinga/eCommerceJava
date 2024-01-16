package com.example.gestaomedicamentos.repository;

import com.example.gestaomedicamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
