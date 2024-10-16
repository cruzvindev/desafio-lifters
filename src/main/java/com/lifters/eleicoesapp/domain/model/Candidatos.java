package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Candidatos extends EntidadeBase{

    private Integer numero;
    private String legenda;

    @ManyToOne
    @JoinColumn(name = "idCargo", referencedColumnName = "id")
    private Cargos cargo;

}
