package com.lifters.eleicoesapp.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
public class EleitorDto implements Serializable {

    @Schema(example = "e0379a7a-042a-4112-a117-05d68792ad63")
    @JsonProperty("eleitorId")
    private UUID id;

    @Schema(example = "Maria Clara Nóbrega")
    @JsonProperty("nomeEleitor")
    private String nome;

    @Schema(example = "09183395024")
    @JsonProperty("eleitorCpf")
    private String cpf;
}
