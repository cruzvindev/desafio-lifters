package com.lifters.eleicoesapp.api.v1.controllers;

import com.lifters.eleicoesapp.api.v1.controllers.openapi.CandidatosControllerOpenApi;
import com.lifters.eleicoesapp.api.v1.dtobuilders.CandidatoDtoConversor;
import com.lifters.eleicoesapp.domain.model.dto.CandidatosDto;
import com.lifters.eleicoesapp.domain.model.dto.RelatorioDto;
import com.lifters.eleicoesapp.domain.model.dto.inputs.CandidatoDtoInput;
import com.lifters.eleicoesapp.domain.service.CandidatoService;
import com.lifters.eleicoesapp.domain.service.CargoService;
import com.lifters.eleicoesapp.domain.service.VotoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/candidatos")
public class CandidatosController implements CandidatosControllerOpenApi {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CandidatoDtoConversor candidatoMapper;

    @Autowired
    private VotoService votoService;

    @Autowired
    private CargoService cargoService;

    @GetMapping("/{id}")
    public CandidatosDto buscarPorId(@PathVariable("id") UUID candidatoId){
        var candidatoBuscado = candidatoService.buscarCandidato(candidatoId);
        return candidatoMapper.converteParaModelo(candidatoBuscado);
    }

    @GetMapping("/listar")
    @Cacheable(value="candidatos")
    public Collection<CandidatosDto> listar(){
        var todosCandidatos = candidatoService.listarCandidatos();
        return candidatoMapper.converteParaColecaoModelo(todosCandidatos);
    }

    @Cacheable(value = "relatorio")
    @GetMapping(path = "/relatorio", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RelatorioDto> getRelatorio() {
        return votoService.gerarRelatorio();
    }

    @GetMapping(path = "/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getRelatorioPdf(){
        byte[] bytesPdf = votoService.gerarRelatorioPdf();
        var headersResposta = new HttpHeaders();
        headersResposta.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=boletim-urna.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headersResposta)
                .body(bytesPdf);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CandidatosDto cadastrar(@RequestBody @Valid CandidatoDtoInput candidatoDtoInput){
        var candidatoSalvo = candidatoMapper.converteParaModeloDominio(candidatoDtoInput);
        candidatoSalvo = candidatoService.salvar(candidatoSalvo);
        return candidatoMapper.converteParaModelo(candidatoSalvo);
    }

    @PutMapping("/{id}")
    public CandidatosDto atualizar(@PathVariable("id") UUID candidatoId, @Valid @RequestBody CandidatoDtoInput candidatoDto) {
        var candidatoBuscado = candidatoService.buscarCandidato(candidatoId);

        var nomeCargo = candidatoDto.getCargo().getNome().toUpperCase();
        cargoService.buscarCargoPorNome(nomeCargo);
        candidatoMapper.copiaParaObjetoDominio(candidatoDto, candidatoBuscado);

        candidatoBuscado = candidatoService.salvar(candidatoBuscado);
        return candidatoMapper.converteParaModelo(candidatoBuscado);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") UUID candidatoId){
        candidatoService.excluir(candidatoId);
    }

}
