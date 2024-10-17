package com.lifters.eleicoesapp.api.v1.dtobuilders;

import com.lifters.eleicoesapp.domain.model.Cargo;
import com.lifters.eleicoesapp.domain.model.dto.CargoDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CargoDtoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CargoDtoConversor {

    @Autowired
    private ModelMapper modelMapper;

    public CargoDto converteParaModelo(Cargo cargo){
        return modelMapper.map(cargo, CargoDto.class);
    }

    public Cargo converteParaModeloDominio(CargoDtoInput cargoInput){
        return modelMapper.map(cargoInput, Cargo.class);
    }

    public Collection<CargoDto> converteParaColecaoModelo(Collection<Cargo> todosCargos) {
        return todosCargos.stream()
                .map(this::converteParaModelo)
                .collect(Collectors.toList());
    }

    public void copiaParaObjetoDominio(CargoDtoInput cargoInput, Cargo cargo){
        modelMapper.map(cargoInput, cargo);
    }
}
