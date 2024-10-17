package com.lifters.eleicoesapp.api.v1.dtobuilders;

import com.lifters.eleicoesapp.domain.model.Candidato;
import com.lifters.eleicoesapp.domain.model.dto.CandidatosDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CandidatoDtoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CandidatoDtoConversor {

    @Autowired
    private ModelMapper modelMapper;

    public CandidatosDto converteParaModelo(Candidato candidato){
        return modelMapper.map(candidato, CandidatosDto.class);
    }

    public Candidato converteParaModeloDominio(CandidatoDtoInput candidatoInput){
        return modelMapper.map(candidatoInput, Candidato.class);
    }

    public Collection<CandidatosDto> converteParaColecaoModelo(Collection<Candidato> todosCandidatos) {
        return todosCandidatos.stream()
                    .map(this::converteParaModelo)
                    .collect(Collectors.toList());
    }

    public void copiaParaObjetoDominio(CandidatoDtoInput candidatoInput, Candidato candidato){
        modelMapper.map(candidatoInput, candidato);
    }
}
