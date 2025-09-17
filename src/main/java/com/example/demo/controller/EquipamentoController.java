package com.example.demo.controller;

import com.example.demo.Model.Arma;
import com.example.demo.Model.Equipamento;
import com.example.demo.service.EquipamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("equipamentos")
public class EquipamentoController {


    private final EquipamentoService service;

    public EquipamentoController (EquipamentoService service){
        this.service = service;
    }

    @GetMapping("{nome}")
    public ResponseEntity<Equipamento> obterArma(@PathVariable("nome") String nome){
        Optional<Equipamento> equipamento = service.obterpornome(nome);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(equipamento.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}


