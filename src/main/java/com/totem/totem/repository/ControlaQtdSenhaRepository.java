package com.totem.totem.repository;

import com.totem.totem.entity.ControlaQtdSenhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlaQtdSenhaRepository extends JpaRepository<ControlaQtdSenhaEntity, Long> {
}
