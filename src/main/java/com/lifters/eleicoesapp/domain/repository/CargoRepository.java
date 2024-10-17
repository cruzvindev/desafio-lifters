package com.lifters.eleicoesapp.domain.repository;

import com.lifters.eleicoesapp.domain.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

    @Query("SELECT c FROM Cargo c WHERE c.id = :cargoId AND c.deletadoEm IS NULL ")
    Optional<Cargo> findByIdActive(@Param("cargoId") UUID cargoId);
    @Query("SELECT c FROM Cargo c WHERE c.deletadoEm IS NULL")
    Collection<Cargo> findAllActive();
    @Query("SELECT c FROM Cargo c WHERE c.nome = :cargoNome AND c.deletadoEm IS NULL ")
    Optional<Cargo> findByNameActive(@Param("cargoNome") String cargoNome);
}
