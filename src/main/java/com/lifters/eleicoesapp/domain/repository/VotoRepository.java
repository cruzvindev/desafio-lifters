package com.lifters.eleicoesapp.domain.repository;

import com.lifters.eleicoesapp.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

    @Query("SELECT v FROM Voto v WHERE v.candidato.cargo.id = :idCargo")
    List<Voto> findVotoByCargoId(@Param("idCargo") UUID idCargo);

    @Query("SELECT v FROM Voto v WHERE v.candidato.id = :idCandidato")
    List<Voto> findVotoByCandidatoId(@Param("idCandidato")UUID candidatoId);

    @Query("SELECT voto FROM Voto voto WHERE voto.eleitor.id = :eleitorId")
    Optional<Voto> findVotoByEleitorId(@Param("eleitorId") UUID eleitorId);
}
