package com.lifters.eleicoesapp.infrastructure.configs;

import com.lifters.eleicoesapp.domain.model.Candidato;
import com.lifters.eleicoesapp.domain.model.Eleitor;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CandidatoDtoInput;
import com.lifters.eleicoesapp.domain.model.dto.inputs.EleitorDtoInput;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
public class ModelMapperConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.typeMap(CandidatoDtoInput.class, Candidato.class)
                .addMappings(mapper -> mapper.map(
                        src -> src.getNumero() != null ? Integer.parseInt(src.getNumero()) : null, Candidato::setNumero));

        modelMapper.typeMap(EleitorDtoInput.class, Eleitor.class)
                .addMappings(mapper -> mapper.map(
                        src -> src.getCpf() != null ? new BigInteger(src.getCpf()) : null,
                        Eleitor::setCpf));

        return modelMapper;
    }
}
