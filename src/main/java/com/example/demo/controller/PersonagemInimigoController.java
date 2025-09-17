package com.example.demo.controller;


import com.example.demo.Model.Arma;
import com.example.demo.Model.DTO.PersonagemInimigoDTO;
import com.example.demo.Model.PersonagemInimigo;
import com.example.demo.service.ArmaService;
import com.example.demo.service.PersonagemInimigoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("inimigo")
public class PersonagemInimigoController {
    private final PersonagemInimigoService service;
    private final ArmaService armaService;


    public PersonagemInimigoController(PersonagemInimigoService service, ArmaService armaService) {
        this.service = service;
        this.armaService = armaService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PersonagemInimigoDTO dto) {
        try {
            PersonagemInimigo inimigo = new PersonagemInimigo(dto.proficienciaDeCombate());

            inimigo.setNome(dto.nome());
            inimigo.setResistencia(dto.resistencia());
            inimigo.setBloqueio(dto.bloqueio());
            inimigo.setProtecao(dto.protecao());
            inimigo.setOdio(dto.odio());
            inimigo.setPoder(dto.poder());
            inimigo.setHabilidadeMortal(dto.habilidadeMortal());

            // Convertendo nomes em entidades Arma
            List<Arma> armas = new ArrayList<>();
            for (String nomeArma : dto.armas()) {
                Optional<Arma> arma = armaService.obterpornome(nomeArma);
                if (arma.isPresent()) {
                    armas.add(arma.get());
                } else {
                    return ResponseEntity
                            .badRequest()
                            .body("Arma com nome '" + nomeArma + "' não encontrada.");
                }
            }

            inimigo.setArmas(armas);

            PersonagemInimigo salvo = service.salvar(inimigo);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar inimigo.");
        }
    }

    @GetMapping("{nome}")
    public ResponseEntity<PersonagemInimigo> obterInimigo(@PathVariable("nome") String nome){
        Optional<PersonagemInimigo> inimigo = service.obterpornome(nome);
        if(inimigo.isPresent()){
            return ResponseEntity.ok(inimigo.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public List<PersonagemInimigo> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idInimigo = UUID.fromString(id);
        Optional<PersonagemInimigo> inimigoOptional = service.obterporID(idInimigo);

        if (inimigoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            service.deletar(inimigoOptional.get());
            return ResponseEntity.noContent().build();
        }
    }

    // @GetMapping
    //public ResponseEntity<List<Arma>> pesquisar (@RequestParam("proficiencia") ProficienciaDeCombate proficienciaDeCombate)

    @PostMapping("/lote")
    public ResponseEntity<?> salvarEmLote(@RequestBody List<PersonagemInimigoDTO> inimigosDto) {
        List<PersonagemInimigo> inimigos = new ArrayList<>();

        for (PersonagemInimigoDTO dto : inimigosDto){
            try {
                PersonagemInimigo inimigo = new PersonagemInimigo(dto.proficienciaDeCombate());

                inimigo.setNome(dto.nome());
                inimigo.setResistencia(dto.resistencia());
                inimigo.setBloqueio(dto.bloqueio());
                inimigo.setProtecao(dto.protecao());
                inimigo.setOdio(dto.odio());
                inimigo.setPoder(dto.poder());
                inimigo.setHabilidadeMortal(dto.habilidadeMortal());

                // Convertendo nomes em entidades Arma
                List<Arma> armas = new ArrayList<>();
                for (String nomeArma : dto.armas()) {
                    Optional<Arma> arma = armaService.obterpornome(nomeArma);
                    if (arma.isPresent()) {
                        armas.add(arma.get());
                    } else {
                        return ResponseEntity
                                .badRequest()
                                .body("Arma com nome '" + nomeArma + "' não encontrada.");
                    }
                }

                inimigo.setArmas(armas);

                inimigos.add(inimigo);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar inimigo.");
            }

        }


        List<PersonagemInimigo> salvas = service.salvarTodas(inimigos);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvas);
    }

    @PatchMapping("/dano/{nome}")
    public ResponseEntity<?> aplicarDano(@PathVariable("nome") String nome, @RequestBody Integer dano) {
        Optional<PersonagemInimigo> opt = service.obterpornome(nome);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemInimigo inimigo = opt.get();
        String resultado = inimigo.receberDano(dano);
        service.salvar(inimigo);

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/ferimento/{nome}")
    public ResponseEntity<?> ferimento(@PathVariable("nome") String nome) {
        Optional<PersonagemInimigo> opt = service.obterpornome(nome);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemInimigo inimigo = opt.get();
        String resultado = inimigo.ferimento();
        service.salvar(inimigo);

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/odio/{nome}")
    public ResponseEntity<?> gastarOdio(@PathVariable("nome") String nome, @RequestBody Integer x) {
        Optional<PersonagemInimigo> opt = service.obterpornome(nome);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemInimigo inimigo = opt.get();
        String resultado = inimigo.gastarOdio(x);
        service.salvar(inimigo);

        return ResponseEntity.ok(resultado);
    }


}
