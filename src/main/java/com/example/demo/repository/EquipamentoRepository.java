package com.example.demo.repository;

import com.example.demo.Model.Arma;
import com.example.demo.Model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EquipamentoRepository extends JpaRepository<Equipamento, UUID> {

    Optional<Equipamento> findByNome(String nome);
}
