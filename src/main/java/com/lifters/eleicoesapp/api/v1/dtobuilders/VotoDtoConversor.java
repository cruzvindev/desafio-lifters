package com.lifters.eleicoesapp.api.v1.dtobuilders;

import com.lifters.eleicoesapp.domain.model.Voto;
import com.lifters.eleicoesapp.domain.model.dto.VotoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.VotoDtoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VotoDtoConversor {

    @Autowired
    private ModelMapper modelMapper;

    public VotoDto converteParaModelo(Voto voto){
        return modelMapper.map(voto, VotoDto.class);
    }

    public Voto converteParaModeloDominio(VotoDtoInput votoDtoInput){
        return modelMapper.map(votoDtoInput, Voto.class);
    }
}
