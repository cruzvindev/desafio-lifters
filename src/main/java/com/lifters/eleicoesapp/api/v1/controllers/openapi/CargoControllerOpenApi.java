package com.lifters.eleicoesapp.api.v1.controllers.openapi;

import com.lifters.eleicoesapp.domain.model.dto.CargoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CargoDtoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;
import java.util.UUID;

@Tag(name = "Cargos", description = "Gerencia os cargos públicos disponíveis para eleição")
public interface CargoControllerOpenApi {

    @Operation(summary = "Busca um cargo por Id")
    public CargoDto buscarPorId(@Parameter(description = "Id de um cargo",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true) UUID cargoId);

    @Operation(summary = "Lista todos os cargos disponíveis")
    public Collection<CargoDto> listar();

    @Operation(summary = "Cadastra um novo cargo")
    public CargoDto cadastrar(@RequestBody(description = "Representação de um novo cargo", required = true)
                                  CargoDtoInput cargoInput);

    @Operation(summary = "Atualiza os dados de um cargo")
    public CargoDto atualizar(@Parameter(description = "Id de um cargo",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)UUID eleitorId,
                              @RequestBody(description = "Representação de um cargo para ser atualizado", required = true)
                              CargoDtoInput cargoInput);

    @Operation(summary = "Deleta um cargo pelo Id")
    public void deletar(@Parameter(description = "Id de um cargo",
            example = "1048df6a-3000-4ac8-9792-4ccd5dc7ed56", required = true)UUID cargoId);
}
