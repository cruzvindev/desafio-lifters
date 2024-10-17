package com.lifters.eleicoesapp.domain.repository;

import com.lifters.eleicoesapp.domain.model.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, UUID> {

    @Query("SELECT e FROM Eleitor e WHERE e.id = :eleitorId AND e.deletadoEm IS NULL ")
    Optional<Eleitor> findByIdActive(UUID eleitorId);
    @Query("SELECT e FROM Eleitor e WHERE e.deletadoEm IS NULL")
    Collection<Eleitor> findAllActive();
    @Query("SELECT e FROM Eleitor e WHERE e.cpf = :eleitorCpf AND e.deletadoEm IS NULL ")
    Optional<Eleitor> findEleitoresByCpf(@Param("eleitorCpf") BigInteger eleitorCpf);
}
