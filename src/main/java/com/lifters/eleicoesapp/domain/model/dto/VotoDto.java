package com.lifters.eleicoesapp.domain.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VotoDto {

    @Schema(example = "7835de27-31f0-4d27-8c29-c399e25f208c")
    private UUID idCandidato;

    @Schema(example = "a166af2c-37ec-4b6f-bdc9-7112ff5f5df3")
    private UUID idEleitor;
}
