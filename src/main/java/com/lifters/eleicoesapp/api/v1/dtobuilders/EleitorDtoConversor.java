package com.lifters.eleicoesapp.api.v1.dtobuilders;

import com.lifters.eleicoesapp.domain.model.Eleitor;
import com.lifters.eleicoesapp.domain.model.dto.EleitorDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.EleitorDtoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EleitorDtoConversor {

    @Autowired
    private ModelMapper modelMapper;

    public EleitorDto converteParaModelo(Eleitor eleitor){
        return modelMapper.map(eleitor, EleitorDto.class);
    }

    public Eleitor converteParaModeloDominio(EleitorDtoInput eleitorInput){
        return modelMapper.map(eleitorInput, Eleitor.class);
    }

    public Collection<EleitorDto> converteParaColecaoModelo(Collection<Eleitor> todosEleitores) {
        return todosEleitores.stream()
                .map(this::converteParaModelo)
                .collect(Collectors.toList());
    }

    public void copiaParaObjetoDominio(EleitorDtoInput eleitorInput, Eleitor eleitor){
        modelMapper.map(eleitorInput, eleitor);
    }
}
