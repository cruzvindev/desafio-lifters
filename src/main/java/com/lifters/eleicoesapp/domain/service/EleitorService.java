package com.lifters.eleicoesapp.domain.service;

import com.lifters.eleicoesapp.domain.exception.EntidadeEmUsoException;
import com.lifters.eleicoesapp.domain.exception.EntidadeNaoEncontradaException;
import com.lifters.eleicoesapp.domain.model.Eleitor;
import com.lifters.eleicoesapp.domain.repository.EleitorRepository;
import com.lifters.eleicoesapp.domain.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepository;

    @Autowired
    private VotoRepository votoRepository;

    public Eleitor buscarEleitor(UUID eleitorId){
        return eleitorRepository.findByIdActive(eleitorId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException
                                (String.format("O eleitor com id %s fornecido não foi localizado", eleitorId.toString())));
    }

    public Collection<Eleitor> listarEleitores() {
        return eleitorRepository.findAllActive();
    }

    @Transactional
    public Eleitor salvar(Eleitor eleitor)  {
        var eleitorBuscado = eleitorRepository.findEleitoresByCpf(eleitor.getCpf());
        if(eleitorBuscado.isPresent()){
            throw new EntidadeEmUsoException(String.format("O eleitor de CPF %s já existe", eleitor.getCpf().toString()));
        };
        return eleitorRepository.save(eleitor);
    }

    @Transactional
    public void excluir(UUID eleitorId) {
        jaVotou(eleitorId);
        var eleitor = buscarEleitor(eleitorId);

        eleitor.setDeletadoEm(LocalDateTime.now());
        eleitorRepository.save(eleitor);
        eleitorRepository.flush();
    }

    public void jaVotou(UUID eleitorId){
        var eleitor = votoRepository.findVotoByEleitorId(eleitorId);
        if(eleitor.isPresent()){
            throw new EntidadeEmUsoException(String.format("O eleitor de id %s já votou e não pode ser excluído nem votar novamente",
                    eleitorId.toString()));
        }
    }
}
