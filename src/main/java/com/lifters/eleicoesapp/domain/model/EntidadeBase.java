package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadeBase {
    @Id
    private UUID id;
    private String nome;
    @CreationTimestamp
    private LocalDateTime criadoEm;
    @UpdateTimestamp
    private LocalDateTime alteradoEm;
    private LocalDateTime deletadoEm;

    @PrePersist
    private void gerarUUID(){
        setId(UUID.randomUUID());
    }
}
