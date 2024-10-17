package com.lifters.eleicoesapp.domain.model.dto.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CargoDtoInput {

    @Schema(example = "PREFEITO")
    @NotBlank
    @JsonProperty("nomeCargo")
    private String nome;
}
