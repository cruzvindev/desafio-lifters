package com.lifters.eleicoesapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eleitores")
public class Eleitor extends EntidadeBase implements Serializable {
    private BigInteger cpf;

    @OneToOne(mappedBy = "eleitor", cascade = CascadeType.ALL)
    private Voto voto;
}
