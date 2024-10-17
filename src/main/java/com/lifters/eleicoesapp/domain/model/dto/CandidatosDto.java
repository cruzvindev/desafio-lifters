package com.lifters.eleicoesapp.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CandidatosDto implements Serializable {

        @Schema(example = "7a07292a-c727-499e-bfc3-0509e9876562")
        @JsonProperty("candidatoId")
        private UUID Id;

        @Schema(example = "João Pedro Costa")
        @JsonProperty("nomeCandidato")
        private String nome;

        @Schema(example = "99")
        @JsonProperty("numeroCandidato")
        private Integer numero;

        @Schema(example = "Juntos pela paz")
        @JsonProperty("legendaCandidato")
        private String legenda;

        @JsonProperty("cargoCandidato")
        private CargoDto cargo;
}
