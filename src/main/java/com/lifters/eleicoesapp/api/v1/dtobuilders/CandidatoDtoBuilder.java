package com.lifters.eleicoesapp.api.v1.dtobuilders;

import com.lifters.eleicoesapp.domain.model.Candidatos;
import com.lifters.eleicoesapp.domain.model.dto.CandidatosDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidatoDtoBuilder {

    @Autowired
    private ModelMapper modelMapper;

    public CandidatosDto converteParaModelo(Candidatos candidato){
        return modelMapper.map(candidato, CandidatosDto.class);
    }
}
