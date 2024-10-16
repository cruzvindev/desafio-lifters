package com.lifters.eleicoesapp.domain.repository;

import com.lifters.eleicoesapp.domain.model.Candidatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidatos, UUID> {
}
