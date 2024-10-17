package com.lifters.eleicoesapp.domain.service;

import com.lifters.eleicoesapp.domain.exception.EntidadeNaoEncontradaException;
import com.lifters.eleicoesapp.domain.model.Candidato;
import com.lifters.eleicoesapp.domain.model.Cargo;
import com.lifters.eleicoesapp.domain.model.Eleitor;
import com.lifters.eleicoesapp.domain.model.Voto;
import com.lifters.eleicoesapp.domain.model.dto.RelatorioDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.VotoDtoInput;
import com.lifters.eleicoesapp.domain.repository.CandidatoRepository;
import com.lifters.eleicoesapp.domain.repository.EleitorRepository;
import com.lifters.eleicoesapp.domain.repository.VotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votosRepository;

    @Autowired
    private CandidatoRepository candidatosRepository;

    @Autowired
    private EleitorRepository eleitorRepository;

    @Autowired
    private EleitorService eleitorService;

    @Autowired
    private ModelMapper modelMapper;

    public List<RelatorioDto> gerarRelatorio() {
        List<Cargo> cargos = candidatosRepository.findAllCargos();
        List<RelatorioDto> relatorio = new ArrayList<>();

        for (Cargo cargo : cargos) {
            List<Voto> votosPorCargo = votosRepository.findVotoByCargoId(cargo.getId());

            Map<Candidato, Long> votosPorCandidato = votosPorCargo.stream()
                    .collect(Collectors.groupingBy(Voto::getCandidato, Collectors.counting()));

            Candidato candidatoVencedor = null;
            long maxVotos = 0;

            for (Map.Entry<Candidato, Long> entry : votosPorCandidato.entrySet()) {
                if (entry.getValue() > maxVotos) {
                    maxVotos = entry.getValue();
                    candidatoVencedor = entry.getKey();
                }
            }

            RelatorioDto dto = new RelatorioDto();
            dto.setIdCargo(cargo.getId());
            dto.setNomeCargo(cargo.getNome());
            dto.setVotos(maxVotos);
            if (candidatoVencedor != null) {
                dto.setIdCandidatoVencedor(candidatoVencedor.getId());
                dto.setNomeCandidatoVencedor(candidatoVencedor.getNome());
            }

            relatorio.add(dto);
        }

        return relatorio;
    }

    @Transactional
    public Voto votar(UUID eleitorId, VotoDtoInput votoDto){

        eleitorService.jaVotou(eleitorId);
        Eleitor eleitor = eleitorRepository.findById(eleitorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("O eleitor de id %s não foi encontrado",
                        votoDto.getEleitor().toString())));

        var candidato = candidatosRepository.findById(votoDto.getCandidato());
        var voto = modelMapper.map(votoDto, Voto.class);
        voto.setEleitor(eleitor);
        voto.setCandidato(candidato.get());

        return votosRepository.save(voto);
    }

}
