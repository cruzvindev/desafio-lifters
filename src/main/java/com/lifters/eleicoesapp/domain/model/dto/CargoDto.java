package com.lifters.eleicoesapp.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CargoDto {

    private UUID id;
    private String nome;
}
