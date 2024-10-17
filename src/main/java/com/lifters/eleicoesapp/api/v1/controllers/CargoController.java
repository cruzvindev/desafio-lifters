package com.lifters.eleicoesapp.api.v1.controllers;

import com.lifters.eleicoesapp.api.v1.controllers.openapi.CargoControllerOpenApi;
import com.lifters.eleicoesapp.api.v1.dtobuilders.CargoDtoConversor;
import com.lifters.eleicoesapp.domain.model.dto.CargoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CargoDtoInput;
import com.lifters.eleicoesapp.domain.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("v1/cargos")
public class CargoController implements CargoControllerOpenApi {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoDtoConversor cargoMapper;

    @GetMapping("/{id}")
    public CargoDto buscarPorId(@PathVariable("id") UUID cargoId){
        var cargoBuscado = cargoService.buscarCargo(cargoId);
        return cargoMapper.converteParaModelo(cargoBuscado);
    }

    @Cacheable("cargos")
    @GetMapping("/listar")
    public Collection<CargoDto> listar(){
        var todosCargos = cargoService.listarCargos();
        return cargoMapper.converteParaColecaoModelo(todosCargos);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CargoDto cadastrar(@RequestBody @Valid CargoDtoInput cargoInput) {
        var cargoSalvo = cargoMapper.converteParaModeloDominio(cargoInput);
        cargoSalvo = cargoService.salvar(cargoSalvo);
        return cargoMapper.converteParaModelo(cargoSalvo);
    }

    @PutMapping("/{id}")
    public CargoDto atualizar(@PathVariable("id") UUID eleitorId, @Valid @RequestBody CargoDtoInput cargoInput){
        var cargoBuscado =  cargoService.buscarCargo(eleitorId);
        cargoMapper.copiaParaObjetoDominio(cargoInput, cargoBuscado);
        cargoBuscado = cargoService.salvar(cargoBuscado);

        return cargoMapper.converteParaModelo(cargoBuscado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") UUID cargoId){
        cargoService.excluir(cargoId);
    }
}
