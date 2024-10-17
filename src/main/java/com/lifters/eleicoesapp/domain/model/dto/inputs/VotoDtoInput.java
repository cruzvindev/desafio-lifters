package com.lifters.eleicoesapp.domain.model.dto.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VotoDtoInput {

    @Schema(example = "7ff148e7-1259-4f25-bee7-5dbcc7b8553d")
    @NotNull
    @JsonProperty("idCandidato")
    private UUID candidato;

    @Schema(example = "daa4f499-c4df-48fa-8a01-520cf0ed2011")
    @NotNull
    @JsonProperty("idEleitor")
    private UUID eleitor;
}
