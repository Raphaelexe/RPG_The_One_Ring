package com.example.demo.service;

import com.example.demo.Model.Arma;
import com.example.demo.Model.PersonagemInimigo;
import com.example.demo.repository.ArmaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArmaService {

    private final ArmaRepository repository;

    public ArmaService(ArmaRepository repository){
        this.repository = repository;
    }

    public void salvar(Arma arma){
        repository.save(arma);
    }

    public List<Arma> salvarTodas(List<Arma> armas) {
        return repository.saveAll(armas);
    }

    public Optional<Arma> obterporID(UUID id){
        return repository.findById(id);
    }

    public Optional<Arma> obterpornome(String nome){
        return repository.findByNome(nome);
    }

    public List<Arma> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Arma arma) {
        repository.delete(arma);
    }

}
