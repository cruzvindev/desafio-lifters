package com.lifters.eleicoesapp.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class RelatorioDto implements Serializable {

    private UUID idCargo;
    private String nomeCargo;
    private Long votos;
    private UUID idCandidatoVencedor;
    private String nomeCandidatoVencedor;

}
