package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Candidatos")
public class Candidato extends EntidadeBase {

    private Integer numero;
    private String legenda;

    @ManyToOne
    @JoinColumn(name = "idCargo", referencedColumnName = "id")
    private Cargo cargo;

}
