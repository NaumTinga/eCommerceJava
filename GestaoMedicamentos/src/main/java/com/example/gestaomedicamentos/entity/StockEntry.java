package com.example.gestaomedicamentos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StockEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantidade;
    @Column
    private LocalDateTime stockEntryDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
