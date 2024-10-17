package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Cargos")
public class Cargo extends EntidadeBase  {

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private Collection<Candidato> candidatos = new HashSet<Candidato>();
}
