package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Data
@Entity
public class Voto implements Serializable {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idCandidato", referencedColumnName = "id")
    private Candidato candidato;

    @OneToOne
    @JoinColumn(name = "idEleitor", referencedColumnName = "id")
    private Eleitor eleitor;

    @CreationTimestamp
    private LocalDateTime data;

    @PrePersist
    private void gerarUUID(){
        setId(UUID.randomUUID());
    }
}
