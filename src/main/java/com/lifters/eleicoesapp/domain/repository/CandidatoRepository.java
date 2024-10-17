package com.lifters.eleicoesapp.domain.repository;

import com.lifters.eleicoesapp.domain.model.Candidato;
import com.lifters.eleicoesapp.domain.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {

    @Query("SELECT c FROM Candidato c WHERE c.id = :candidatoId AND c.deletadoEm IS NULL ")
    Optional<Candidato> findByIdActive(@Param("candidatoId") UUID candidatoId);
    @Query("SELECT c FROM Candidato c WHERE c.deletadoEm IS NULL")
    Collection<Candidato> findAllActive();
    @Query("SELECT c.cargo FROM Candidato c WHERE c.deletadoEm IS NULL GROUP BY c.cargo")
    List<Cargo> findAllCargos();

}
