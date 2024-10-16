package com.lifters.eleicoesapp.api.v1.controllers;

import com.lifters.eleicoesapp.api.v1.dtobuilders.CandidatoDtoBuilder;
import com.lifters.eleicoesapp.domain.model.dto.CandidatosDto;
import com.lifters.eleicoesapp.domain.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CandidatoDtoBuilder candidatoBuilder;

    @GetMapping("/{id}")
    public CandidatosDto buscarPorId(@PathVariable("id") UUID candidatoId){
        var candidatoBuscado = candidatoService.buscarCandidato(candidatoId);
        return candidatoBuilder.converteParaModelo(candidatoBuscado);
    }
}
