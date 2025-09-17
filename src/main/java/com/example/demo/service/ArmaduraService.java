package com.example.demo.service;

import com.example.demo.Model.Armadura;
import com.example.demo.repository.ArmaduraRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ArmaduraService {

    private final ArmaduraRepository repository;

    public ArmaduraService(ArmaduraRepository repository){
        this.repository = repository;
    }

    public void salvar(Armadura armadura){
        repository.save(armadura);
    }

    public List<Armadura> salvarTodas(List<Armadura> armaduras) {
        return repository.saveAll(armaduras);
    }

    public Optional<Armadura> obterporID(UUID id){
        return repository.findById(id);
    }

    public Optional<Armadura> obterpornome(String nome){
        return repository.findByNome(nome);
    }

    public List<Armadura> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Armadura armadura) {
        repository.delete(armadura);
    }
}
