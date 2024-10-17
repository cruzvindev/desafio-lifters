package com.lifters.eleicoesapp.api.v1.controllers;

import com.lifters.eleicoesapp.api.v1.controllers.openapi.EleitoresControllerOpenApi;
import com.lifters.eleicoesapp.api.v1.dtobuilders.EleitorDtoConversor;
import com.lifters.eleicoesapp.api.v1.dtobuilders.VotoDtoConversor;
import com.lifters.eleicoesapp.domain.model.dto.EleitorDto;
import com.lifters.eleicoesapp.domain.model.dto.VotoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.EleitorDtoInput;
import com.lifters.eleicoesapp.domain.model.dto.inputs.VotoDtoInput;
import com.lifters.eleicoesapp.domain.service.EleitorService;
import com.lifters.eleicoesapp.domain.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("v1/eleitores")
public class EleitoresController implements EleitoresControllerOpenApi {

    @Autowired
    private EleitorService eleitorService;

    @Autowired
    private EleitorDtoConversor eleitorMapper;

    @Autowired
    private VotoService votoService;

    @Autowired
    private VotoDtoConversor votoConversor;

    @GetMapping("/{id}")
    public EleitorDto buscarPorId(@PathVariable("id") UUID eleitorId){
        var eleitorBuscado = eleitorService.buscarEleitor(eleitorId);
        return eleitorMapper.converteParaModelo(eleitorBuscado);
    }

    @Cacheable("eleitores")
    @GetMapping("/listar")
    public Collection<EleitorDto> listar(){
        var todosEleitores = eleitorService.listarEleitores();
        return eleitorMapper.converteParaColecaoModelo(todosEleitores);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EleitorDto cadastrar(@RequestBody @Valid EleitorDtoInput eleitorDtoInput) {
        var eleitorSalvo = eleitorMapper.converteParaModeloDominio(eleitorDtoInput);
        eleitorSalvo = eleitorService.salvar(eleitorSalvo);
        return eleitorMapper.converteParaModelo(eleitorSalvo);
    }

    @PostMapping("/{id}/votar")
    @ResponseStatus(HttpStatus.CREATED)
    public VotoDto votar(@PathVariable("id") UUID eleitorId,@RequestBody @Valid VotoDtoInput votoDto)  {
            var votacao = votoService.votar(eleitorId, votoDto);
            return votoConversor.converteParaModelo(votacao);
    }

    @PutMapping("/{id}")
    public EleitorDto atualizar(@PathVariable("id") UUID eleitorId, @Valid @RequestBody EleitorDtoInput eleitoresDto) {
        var eleitorBuscado = eleitorService.buscarEleitor(eleitorId);
        eleitorMapper.copiaParaObjetoDominio(eleitoresDto, eleitorBuscado);
        eleitorBuscado = eleitorService.salvar(eleitorBuscado);

        return eleitorMapper.converteParaModelo(eleitorBuscado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") UUID eleitorId){
        eleitorService.excluir(eleitorId);
    }
}
