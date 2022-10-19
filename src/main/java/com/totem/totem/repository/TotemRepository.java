package com.totem.totem.repository;

import com.totem.totem.entity.TotemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TotemRepository extends JpaRepository<TotemEntity, Long> {
    Optional<TotemEntity> findBySenha(@Param("senha") String senha);
}
