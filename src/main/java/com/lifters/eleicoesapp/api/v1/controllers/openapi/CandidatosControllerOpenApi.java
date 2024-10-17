package com.lifters.eleicoesapp.api.v1.controllers.openapi;

import com.lifters.eleicoesapp.domain.model.dto.CandidatosDto;
import com.lifters.eleicoesapp.domain.model.dto.RelatorioDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CandidatoDtoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Tag(name = "Candidatos", description = "Gerencia os candidatos a eleição")
public interface CandidatosControllerOpenApi {

    @Operation(summary = "Busca um candidato por Id", responses= {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Id de candidato inválido", content = @Content(schema = @Schema))
    })
    public CandidatosDto buscarPorId(@Parameter(description = "Id de um candidato",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true) UUID candidatoId);

    @Operation(summary = "Lista todos os candidatos disponíveis")
    public Collection<CandidatosDto> listar();
    @Operation(summary = "Traz um relatório contendo os candidatos eleitos para cada cargo disponível")
    public List<RelatorioDto> getRelatorio();

    @Operation(summary = "Cadastra um novo candidato")
    public CandidatosDto cadastrar(@RequestBody(description = "Representação de um novo candidato", required = true)
                                       CandidatoDtoInput candidatoDtoInput);

    @Operation(summary = "Atualiza os dados de um candidato")
    public CandidatosDto atualizar(@Parameter(description = "Id de um candidato", example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)
                                       UUID candidatoId,
                                   @RequestBody(description = "Representação de um candidato para ser atualizado", required = true)
                                   CandidatoDtoInput candidatoDto);

    @Operation(summary = "Deleta um candidato pelo Id")
    public void deletar(@Parameter(description = "Id de um candidato",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true) UUID candidatoId);

}
