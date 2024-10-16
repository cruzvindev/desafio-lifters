package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Data
@Entity
public class Votos {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idCandidato", referencedColumnName = "id")
    private Candidatos candidato;

    @OneToOne
    @JoinColumn(name = "idEleitor", referencedColumnName = "id")
    private Eleitores eleitor;

    @CreationTimestamp
    private LocalDateTime data;
}
