package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import java.util.Collections;

@MappedSuperclass
@Data
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    protected int resistencia;

    protected int bloqueio;

    protected int protecao;

    protected int profMachados;

    protected int profArcos;

    protected int profLancas;

    protected int profEspadas;

    protected int profBriga;


    public Personagem(){

    }

    public Personagem(List<Integer> proficienciaDeCombate) {

        if(proficienciaDeCombate != null && proficienciaDeCombate.size()==4){
            this.profMachados = proficienciaDeCombate.get(0);
            this.profArcos = proficienciaDeCombate.get(1);
            this.profLancas = proficienciaDeCombate.get(2);
            this.profEspadas = proficienciaDeCombate.get(3);
            this.profBriga = Collections.max(proficienciaDeCombate);
        }
        else {
            throw new IllegalArgumentException("O vetor de proficiência de combate precisa ter 4 entradas");
        }
    }


}
