package com.lifters.eleicoesapp.domain.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RelatorioDto {

    private UUID idCargo;
    private String nomeCargo;
    private Long votos;
    private UUID idCandidatoVencedor;
    private String nomeCandidatoVencedor;

}
