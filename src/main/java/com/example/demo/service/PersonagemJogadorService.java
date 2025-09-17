package com.example.demo.service;

import com.example.demo.Model.PersonagemJogador;
import com.example.demo.repository.PersonagemJogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonagemJogadorService {

    private final PersonagemJogadorRepository repository;

    public PersonagemJogadorService(PersonagemJogadorRepository repository) {
        this.repository = repository;
    }

    public PersonagemJogador salvar(PersonagemJogador jogador) {
        return repository.save(jogador);
    }

    public void atualizar(PersonagemJogador jogador) {
        if (jogador.getId()==null){
            throw new IllegalArgumentException("É necessário que o jogador já esteja salvo");
        }
       repository.save(jogador);
    }

    public List<PersonagemJogador> salvarTodas(List<PersonagemJogador> jogadores) {
        return repository.saveAll(jogadores);
    }

    public List<PersonagemJogador> listarTodos() {
        return repository.findAll();
    }

    public Optional<PersonagemJogador> obterporID(UUID id){
        return repository.findById(id);
    }

    public Optional<PersonagemJogador> obterporNomeDoJogador(String nome){
        return repository.findByNomeDoJogador(nome);
    }

    public void deletar(PersonagemJogador jogador) {
        repository.delete(jogador);
    }



}
