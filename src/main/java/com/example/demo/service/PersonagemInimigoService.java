package com.example.demo.service;


import com.example.demo.Model.Arma;
import com.example.demo.Model.PersonagemInimigo;
import com.example.demo.repository.PersonagemInimigoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonagemInimigoService {

    private final PersonagemInimigoRepository repository;

    public PersonagemInimigoService(PersonagemInimigoRepository repository) {
        this.repository = repository;
    }

    public PersonagemInimigo salvar(PersonagemInimigo inimigo) {
        return repository.save(inimigo);
    }

    public List<PersonagemInimigo> salvarTodas(List<PersonagemInimigo> inimigos) {
        return repository.saveAll(inimigos);
    }

    public List<PersonagemInimigo> listarTodos() {
        return repository.findAll();
    }

    public Optional<PersonagemInimigo> obterporID(UUID id){
        return repository.findById(id);
    }

    public Optional<PersonagemInimigo> obterpornome(String nome){
        return repository.findByNome(nome);
    }

    public void deletar(PersonagemInimigo inimigo) {
        repository.delete(inimigo);
    }

}
