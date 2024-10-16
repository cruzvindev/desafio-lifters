package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Cargos extends EntidadeBase{

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Candidatos> candidatos = new ArrayList<>();
}
