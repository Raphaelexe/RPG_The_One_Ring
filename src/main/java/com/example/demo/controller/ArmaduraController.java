package com.example.demo.controller;

import com.example.demo.Model.Armadura;
import com.example.demo.service.ArmaduraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("armaduras")
//http://localhost:8080/armaduras
public class ArmaduraController {

    private final ArmaduraService service;

    public ArmaduraController (ArmaduraService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar (@RequestBody Armadura armadura){
        service.salvar(armadura);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(armadura.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{nome}")
    public ResponseEntity<Armadura> obterArmadura(@PathVariable("nome") String nome){
        Optional<Armadura> armadura = service.obterpornome(nome);
        if(armadura.isPresent()){
            return ResponseEntity.ok(armadura.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public List<Armadura> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idArmadura = UUID.fromString(id);
        Optional<Armadura> armaduraOptional = service.obterporID(idArmadura);

        if (armaduraOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            service.deletar(armaduraOptional.get());
            return ResponseEntity.noContent().build();
        }
    }

    // @GetMapping
    //public ResponseEntity<List<Armadura>> pesquisar (@RequestParam("proficiencia") ProficienciaDeCombate proficienciaDeCombate)

    @PostMapping("/lote")
    public ResponseEntity<?> salvarEmLote(@RequestBody List<Armadura> armaduras) {
        List<Armadura> salvas = service.salvarTodas(armaduras);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvas);
    }


}
