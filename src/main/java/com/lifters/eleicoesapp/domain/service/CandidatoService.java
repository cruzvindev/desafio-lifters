package com.lifters.eleicoesapp.domain.service;

import com.lifters.eleicoesapp.domain.model.Candidatos;
import com.lifters.eleicoesapp.domain.repository.CandidatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Candidatos buscarCandidato(UUID candidatoID){
        return candidatoRepository.findById(candidatoID)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
