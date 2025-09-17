package com.example.demo.repository;

import com.example.demo.Model.Arma;
import com.example.demo.Model.Armadura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArmaduraRepository extends JpaRepository<Armadura, UUID> {
    Optional<Armadura> findByNome(String nome);
}
