package com.lifters.eleicoesapp.domain.service;

import com.lifters.eleicoesapp.domain.exception.EntidadeEmUsoException;
import com.lifters.eleicoesapp.domain.exception.EntidadeNaoEncontradaException;
import com.lifters.eleicoesapp.domain.model.Cargo;
import com.lifters.eleicoesapp.domain.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public Cargo buscarCargo(UUID cargoId){
        return cargoRepository.findByIdActive(cargoId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException
                                (String.format("O cargo com id %s fornecido não foi localizado", cargoId.toString())));
    }

    public Cargo buscarCargoPorNome(String nomeCargo){
        return cargoRepository.findByNameActive(nomeCargo)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException
                                (String.format("O cargo de nome %s fornecido não foi localizado", nomeCargo)));
    }

    public Collection<Cargo> listarCargos() {
        return cargoRepository.findAllActive();
    }

    @Transactional
    public Cargo salvar(Cargo cargo){
        cargo.setNome(cargo.getNome().toUpperCase());
        var cargoBuscado = cargoRepository.findByNameActive(cargo.getNome());

        if(cargoBuscado.isPresent()){
            throw new EntidadeEmUsoException(String.format("O cargo com nome %s fornecido já existe", cargo.getNome()));
        }

        return cargoRepository.save(cargo);
    }

    @Transactional
    public void excluir(UUID cargoId) {
        var cargo = buscarCargo(cargoId);
        cargo.setDeletadoEm(LocalDateTime.now());
        cargoRepository.save(cargo);
        cargoRepository.flush();
    }
}
