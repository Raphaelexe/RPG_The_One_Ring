package com.example.demo.controller;


import com.example.demo.Model.Arma;
import com.example.demo.Model.DTO.PersonagemInimigoDTO;
import com.example.demo.Model.DTO.PersonagemJogadorDTO;
import com.example.demo.Model.Enums.PadraoDeVida;
import com.example.demo.Model.Equipamento;
import com.example.demo.Model.PersonagemInimigo;
import com.example.demo.Model.PersonagemJogador;
import com.example.demo.service.ArmaService;
import com.example.demo.service.EquipamentoService;
import com.example.demo.service.PersonagemInimigoService;
import com.example.demo.service.PersonagemJogadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("jogador")
public class PersonagemJogardoController {

    private final PersonagemJogadorService service;
    private final EquipamentoService equipamentoService;

    public PersonagemJogardoController(PersonagemJogadorService service, EquipamentoService equipamentoService) {
        this.service = service;
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PersonagemJogadorDTO dto){
        try{
            List<Equipamento> equipamentos = equipamentoService.obterPornomeLista(dto.equipamento());

            PersonagemJogador jogador = new PersonagemJogador();

            jogador.setProfCombate(dto.proficienciaDeCombate());
            jogador.setForca(dto.nivelForca());
            jogador.setListaForca(dto.listaForca());
            jogador.setAstucia(dto.nivelAstucia());
            jogador.setListaAstucia(dto.listaAstucia());
            jogador.setCoracao(dto.nivelCoracao());
            jogador.setListaCoracao(dto.listaCoracao());
            jogador.setEquipamentos(equipamentos);
            jogador.setBloqueio(dto.bloqueio());
            jogador.setResistencia(dto.resistencia());
            jogador.setEsperanca(dto.esperanca());
            jogador.setSombra(dto.sombra());


            jogador.setNome(dto.nome());
            jogador.setNomeDoJogador(dto.nomeDoJogador());
            jogador.setCulturaHeroica(dto.culturaHeroica());
            jogador.setIdade(dto.idade());
            jogador.setPadraoDeVida(dto.padraoDeVida());
            jogador.setTesouro(dto.tesouro());
            jogador.setCaracteristicasNotaveis(dto.caracteristicasNotaveis());
            jogador.setBencaoCultural(dto.bencaoCultural());
            jogador.setPatrono(dto.patrono());
            jogador.setChamado(dto.chamado());
            jogador.setCaminhoDasSombras(dto.caminhoDasSombras());
            jogador.setFalhas(dto.falhas());

            jogador.setRecompensa(dto.recompensa());
            jogador.setValor(dto.valor());
            jogador.setVirtudes(dto.virtudes());
            jogador.setSabedoria(dto.sabedoria());

            jogador.setPontosDeAventura(dto.pontosDeAventura());
            jogador.setPontosDePericia(dto.pontosDePericia());
            jogador.setPontosDeSociedade(dto.pontosDeSociedade());
            jogador.setFadiga(dto.fadiga());

            jogador.setExausto(dto.exausto());
            jogador.setArrasado(dto.arrasado());
            jogador.setFerido(dto.ferido());
            jogador.setFerimento(dto.ferimento());
            jogador.setEquipamentoDeViagem(dto.equipamentoDeViagem());


            PersonagemJogador salvo = service.salvar(jogador);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar personagem jogador: " + e.getMessage());
        }
    }

    @GetMapping("{nome}")
    public ResponseEntity<PersonagemJogador> obterJogador(@PathVariable("nome") String nome){
        Optional<PersonagemJogador> jogador = service.obterporNomeDoJogador(nome);
        if(jogador.isPresent()){
            return ResponseEntity.ok(jogador.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping
    public List<PersonagemJogador> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idJogador = UUID.fromString(id);
        Optional<PersonagemJogador> jogadorOptional = service.obterporID(idJogador);

        if (jogadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            service.deletar(jogadorOptional.get());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id")String id,
            @RequestBody PersonagemJogadorDTO dto){
        var idJogador = UUID.fromString(id);
        Optional<PersonagemJogador> jogadorOptional = service.obterporID(idJogador);

        if (jogadorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            try{
                var jogador = jogadorOptional.get();
                List<Equipamento> equipamentos = new ArrayList<>();
                for (String nomeEquipamento : dto.equipamento()) {
                    Optional<Equipamento> equipamento = equipamentoService.obterpornome(nomeEquipamento);
                    if (equipamento.isPresent()) {
                        equipamentos.add(equipamento.get());
                    } else {
                        return ResponseEntity
                                .badRequest().body("Equipamento com nome '" + nomeEquipamento + "' não encontrada.");
                    }
                }

                jogador.setForca(dto.nivelForca());
                jogador.setListaForca(dto.listaForca());
                jogador.setAstucia(dto.nivelAstucia());
                jogador.setListaAstucia(dto.listaAstucia());
                jogador.setCortesia(dto.nivelCoracao());
                jogador.setListaCoracao(dto.listaCoracao());
                jogador.setEquipamentos(equipamentos);
                jogador.setBloqueio(dto.bloqueio());
                jogador.setResistencia(dto.resistencia());
                jogador.setEsperanca(dto.esperanca());
                jogador.setSombra(dto.sombra());


                jogador.setNome(dto.nome());
                jogador.setNomeDoJogador(dto.nomeDoJogador());
                jogador.setCulturaHeroica(dto.culturaHeroica());
                jogador.setIdade(dto.idade());
                jogador.setPadraoDeVida(dto.padraoDeVida());
                jogador.setTesouro(dto.tesouro());
                jogador.setCaracteristicasNotaveis(dto.caracteristicasNotaveis());
                jogador.setBencaoCultural(dto.bencaoCultural());
                jogador.setPatrono(dto.patrono());
                jogador.setChamado(dto.chamado());
                jogador.setCaminhoDasSombras(dto.caminhoDasSombras());
                jogador.setFalhas(dto.falhas());

                jogador.setRecompensa(dto.recompensa());
                jogador.setValor(dto.valor());
                jogador.setVirtudes(dto.virtudes());
                jogador.setSabedoria(dto.sabedoria());

                jogador.setPontosDeAventura(dto.pontosDeAventura());
                jogador.setPontosDePericia(dto.pontosDePericia());
                jogador.setPontosDeSociedade(dto.pontosDeSociedade());
                jogador.setFadiga(dto.fadiga());

                jogador.setExausto(dto.exausto());
                jogador.setArrasado(dto.arrasado());
                jogador.setFerido(dto.ferido());
                jogador.setFerimento(dto.ferimento());
                jogador.setEquipamentoDeViagem(dto.equipamentoDeViagem());


                service.atualizar(jogador);
                return ResponseEntity.noContent().build();

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar personagem jogador: " + e.getMessage());
            }
        }

    }

    @PatchMapping("{nome}")
    public ResponseEntity<?> atualizarParcialmente(@PathVariable("nome") String nome,
                                                   @RequestBody Map<String, Object> updates) {
        Optional<PersonagemJogador> opt = service.obterporNomeDoJogador(nome);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemJogador jogador = opt.get();


        updates.forEach((chave, valor) -> {
            switch (chave) {

                case "resistencia" -> jogador.setResistencia((Integer) valor);
                case "bloqueio" -> jogador.setBloqueio((Integer) valor);
                case "protecao" -> jogador.setProtecao((Integer) valor);
                case "proficienciaDeCombate" -> jogador.setProfCombate((List<Integer>) valor);
                case "equipamento" -> jogador.setEquipamentos(equipamentoService.obterPornomeLista((List<String>) valor));

                case "idade" -> jogador.setIdade((Integer) valor);
                case "padraoDeVida" -> jogador.setPadraoDeVida(PadraoDeVida.valueOf((String) valor));
                case "tesouro" -> jogador.setTesouro((Integer) valor);
                case "caracteristicasNotaveis" -> jogador.setCaracteristicasNotaveis((String) valor);


                case "falhas" -> jogador.setFalhas((String) valor);
                case "nivelForca" -> jogador.setNivelForca((Integer) valor);
                case "listaForca" -> jogador.setListaForca((List<Integer>) valor);

                case "nivelCoracao" -> jogador.setNivelCoracao((Integer) valor);
                case "listaCoracao" -> jogador.setListaCoracao((List<Integer>) valor);

                case "nivelAstucia" -> jogador.setNivelAstucia((Integer) valor);
                case "listaAstucia" -> jogador.setListaAstucia((List<Integer>) valor);

                case "recompensa" -> jogador.setRecompensa((String) valor);
                case "valor" -> jogador.setValor((Integer) valor);

                case "virtudes" -> jogador.setVirtudes((String) valor);
                case "sabedoria" -> jogador.setSabedoria((Integer) valor);

                case "pontosDeAventura" -> jogador.setPontosDeAventura((Integer) valor);
                case "pontosDePericia" -> jogador.setPontosDePericia((Integer) valor);
                case "pontosDeSociedade" -> jogador.setPontosDeSociedade((Integer) valor);

                case "fadiga" -> jogador.setFadiga((Integer) valor);
                case "sombra" -> jogador.setSombra((Integer) valor);

                case "exausto" -> jogador.setExausto((Boolean) valor);
                case "arrasado" -> jogador.setArrasado((Boolean) valor);
                case "ferido" -> jogador.setFerido((Boolean) valor);
                case "ferimento" -> jogador.setFerimento((Integer) valor);

                case "equipamentoDeViagem" -> jogador.setEquipamentoDeViagem((String) valor);
                case "esperanca" -> jogador.setEsperanca((Integer) valor);

            }
        });
        service.salvar(jogador);
        return ResponseEntity.ok("Personagem atualizado com sucesso.");
    }

    @PatchMapping("/dano/{nome}")
    public ResponseEntity<?> aplicarDano(@PathVariable("nome") String nome, @RequestBody Integer dano) {
        Optional<PersonagemJogador> opt = service.obterporNomeDoJogador(nome);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemJogador jogador = opt.get();
        String resultado = jogador.receberDano(dano);
        service.salvar(jogador);

        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/ferimento/{nome}")
    public ResponseEntity<?> ferimento(@PathVariable("nome") String nome, @RequestBody Integer dado) {
        Optional<PersonagemJogador> opt = service.obterporNomeDoJogador(nome);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonagemJogador jogador = opt.get();
        String resultado = jogador.ferimento(dado);
        service.salvar(jogador);

        return ResponseEntity.ok(resultado);
    }


}




