package com.example.demo.repository;

import com.example.demo.Model.Arma;
import com.example.demo.Model.PersonagemInimigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonagemInimigoRepository extends JpaRepository<PersonagemInimigo, UUID> {
    Optional<PersonagemInimigo> findByNome(String nome);
}
