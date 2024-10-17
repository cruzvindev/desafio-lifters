package com.lifters.eleicoesapp.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CargoDto implements Serializable {

    @Schema(example = "e0379a7a-042a-4112-a117-05d68792ad63")
    @JsonProperty("cargoId")
    private UUID id;

    @Schema(example = "PRESIDENTE")
    @JsonProperty("nomeCargo")
    private String nome;
}
