package com.totem.totem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "totem")
public class TotemEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "senha")
    private String senha;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "prioridade")
    private String prioridade;

    public TotemEntity(){}
}
