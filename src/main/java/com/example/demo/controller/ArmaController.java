package com.example.demo.controller;

import com.example.demo.Model.Arma;
import com.example.demo.service.ArmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("armas")
//http://localhost:8080/armas
public class ArmaController {

    private final ArmaService service;

    public ArmaController (ArmaService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar (@RequestBody Arma arma){
        service.salvar(arma);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(arma.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{nome}")
    public ResponseEntity<Arma> obterArma(@PathVariable("nome") String nome){
        Optional<Arma> arma = service.obterpornome(nome);
        if(arma.isPresent()){
            return ResponseEntity.ok(arma.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public List<Arma> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idArma = UUID.fromString(id);
        Optional<Arma> armaOptional = service.obterporID(idArma);

        if (armaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            service.deletar(armaOptional.get());
            return ResponseEntity.noContent().build();
        }
    }

   // @GetMapping
    //public ResponseEntity<List<Arma>> pesquisar (@RequestParam("proficiencia") ProficienciaDeCombate proficienciaDeCombate)

    @PostMapping("/lote")
    public ResponseEntity<?> salvarEmLote(@RequestBody List<Arma> armas) {
        List<Arma> salvas = service.salvarTodas(armas);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvas);
    }


}
