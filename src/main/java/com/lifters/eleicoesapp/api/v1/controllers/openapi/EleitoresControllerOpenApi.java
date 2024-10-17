package com.lifters.eleicoesapp.api.v1.controllers.openapi;

import com.lifters.eleicoesapp.domain.model.dto.EleitorDto;
import com.lifters.eleicoesapp.domain.model.dto.VotoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.EleitorDtoInput;
import com.lifters.eleicoesapp.domain.model.dto.inputs.VotoDtoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "Eleitores", description = "Gerencia os eleitores")
public interface EleitoresControllerOpenApi {


    @Operation(summary = "Busca um eleitor por Id")
    public EleitorDto buscarPorId(@Parameter(description = "Id de um eleitor",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)UUID eleitorId);

    @Operation(summary = "Lista todos os eleitores")
    public Collection<EleitorDto> listar();

    @Operation(summary = "Cadastra um novo eleitor")
    public EleitorDto cadastrar(EleitorDtoInput eleitorDtoInput);

    @Operation(summary = "Cadastra o voto de um eleitor em um candidato")
    public VotoDto votar(@Parameter(description = "Id de um eleitor",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true) UUID eleitorId,
                         @RequestBody(description = "Representação de um voto para ser cadastrado", required = true)
                         VotoDtoInput votoDto);

    @Operation(summary = "Atualiza os dados de um eleitor")
    public EleitorDto atualizar(@Parameter(description = "Id de um eleitor",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)UUID eleitorId,
                                @RequestBody(description = "Representação de um eleitor para ser atualizado", required = true)
                                EleitorDtoInput eleitoresDto);

    @Operation(summary = "Deleta um eleitor pelo Id")
    public void deletar(@Parameter(description = "Id de um eleitor",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)UUID eleitorId);
}
