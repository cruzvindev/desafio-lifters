package com.lifters.eleicoesapp.domain.model.dto.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lifters.eleicoesapp.domain.model.dto.CargoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CandidatoDtoInput {

    @Schema(example = "João Paulo Figueiredo")
    @Size(min = 2, max = 255)
    @NotBlank
    @JsonProperty("nomeCandidato")
    private String nome;

    @Schema(example = "27")
    @Length(min = 2, max = 5)
    @JsonProperty("numeroCandidato")
    private String numero;

    @Schema(example = "Juntos pelas minorias")
    @Size(min = 2, max = 255)
    @NotBlank
    @JsonProperty("legendaCandidato")
    private String legenda;

    @NotNull
    @JsonProperty("cargoCandidato")
    private CargoDtoInput cargo;

}
