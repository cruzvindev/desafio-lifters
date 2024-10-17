package com.lifters.eleicoesapp.domain.model.dto.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Getter
@Setter
public class EleitorDtoInput  {

    @Schema(example = "Lucas Santos")
    @NotBlank
    @JsonProperty("nomeEleitor")
    private String nome;

    @Schema(example = "38289068000")
    @CPF
    @NotBlank
    @JsonProperty("eleitorCpf")
    private String cpf;

}
