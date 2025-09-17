package com.example.demo.repository;

import com.example.demo.Model.Arma;
import com.example.demo.Model.PersonagemInimigo;
import com.example.demo.Model.PersonagemJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PersonagemJogadorRepository extends JpaRepository<PersonagemJogador, UUID> {
    Optional<PersonagemJogador> findByNomeDoJogador(String nomeDoJogador);
}
