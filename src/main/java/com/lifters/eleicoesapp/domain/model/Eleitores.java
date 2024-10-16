package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Eleitores extends EntidadeBase{
    private Integer CPF;

    @OneToOne(mappedBy = "eleitor", cascade = CascadeType.ALL)
    private Votos voto;
}
