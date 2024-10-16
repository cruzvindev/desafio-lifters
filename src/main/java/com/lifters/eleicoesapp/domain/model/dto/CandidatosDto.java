package com.lifters.eleicoesapp.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CandidatosDto {
        private UUID Id;
        private String nome;
        private Integer numero;
        private String legenda;
        private CargoDto cargo;
}
