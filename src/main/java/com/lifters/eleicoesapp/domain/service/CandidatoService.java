package com.lifters.eleicoesapp.domain.service;

import com.lifters.eleicoesapp.domain.exception.EntidadeEmUsoException;
import com.lifters.eleicoesapp.domain.exception.EntidadeNaoEncontradaException;
import com.lifters.eleicoesapp.domain.model.Candidato;
import com.lifters.eleicoesapp.domain.model.Cargo;
import com.lifters.eleicoesapp.domain.repository.CandidatoRepository;
import com.lifters.eleicoesapp.domain.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private VotoRepository votoRepository;

    public Candidato buscarCandidato(UUID candidatoId){
        return candidatoRepository.findByIdActive(candidatoId)
                .orElseThrow(() -> new
                        EntidadeNaoEncontradaException(
                                String.format("O candidato com id %s fornecido não foi localizado", candidatoId.toString())));
    }

    public Collection<Candidato> listarCandidatos() {
        return candidatoRepository.findAllActive();
    }

    @Transactional
    public Candidato salvar(Candidato candidato) {

        UUID cargoId = candidato.getCargo().getId();
        String cargoNome = candidato.getCargo().getNome();
        Cargo cargo = cargoService.buscarCargoPorNome(cargoNome.toUpperCase());
        candidato.setCargo(cargo);
        return candidatoRepository.save(candidato);
    }

    @Transactional
    public void excluir(UUID candidatoId) {
        jaRecebeuVoto(candidatoId);
        var candidato = buscarCandidato(candidatoId);
        candidato.setDeletadoEm(LocalDateTime.now());
        candidatoRepository.save(candidato);
        candidatoRepository.flush();
    }

    public void jaRecebeuVoto(UUID candidatoId){
        var votosBuscados = votoRepository.findVotoByCandidatoId(candidatoId);
        if(!votosBuscados.isEmpty()){
            throw new EntidadeEmUsoException(
                    String.format("O candidato de id %s já possui votos e portanto não pode ser excluído", candidatoId));
        }
    }
}
