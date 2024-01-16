package com.example.gestaomedicamentos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String category;

    private String tipo;

    public Medicine() {
    }

    public Medicine(Long id, String name, String category, String tipo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
