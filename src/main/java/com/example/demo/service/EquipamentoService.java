package com.example.demo.service;

import com.example.demo.Model.Arma;
import com.example.demo.Model.Equipamento;
import com.example.demo.repository.EquipamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService (EquipamentoRepository repository){
        this.repository=repository;
    }

    public Optional<Equipamento> obterpornome(String nome){
        return repository.findByNome(nome);

    }

    public List<Equipamento> obterPornomeLista(List<String> nomes){
        List<Equipamento> equipamentos = new ArrayList<>();
        for (String nomeEquipamento : nomes) {
            Optional<Equipamento> equipamento = repository.findByNome(nomeEquipamento);
            if (equipamento.isPresent()) {
                equipamentos.add(equipamento.get());
            } else {
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Equipamento com nome '" + nomeEquipamento + "' não encontrado."
                );
            }
        }

        return equipamentos;
    }
}
