package com.totem.totem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ControlaQtdSenhaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade_senha")
    private int qtdSenhaGeradaAtual;
}
