package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class PersonagemInimigo extends Personagem {

   private int poder;

   private int odio;

   private String habilidadeMortal;


    @ManyToMany
    @JoinTable(
            name = "personagem_inimigo_arma",
            joinColumns = @JoinColumn(name = "personagem_id"),
            inverseJoinColumns = @JoinColumn(name = "arma_id")
    )
    private List<Arma> armas;


    public PersonagemInimigo(){
    }

    public PersonagemInimigo(List<Integer> proficienciaDeCombate) {
      super(proficienciaDeCombate);
    }

    public String receberDano(int dano) {
        this.setResistencia(this.getResistencia() - dano);
        return String.format("A resistência atual de %s é %d", this.getNome(), this.getResistencia());
    }

    public String ferimento() {
        this.setPoder(this.getPoder() - 1);
        return String.format("O poder atual de %s é %d", this.getNome(), this.getPoder());
    }

    public String gastarOdio(int valor) {
        this.setOdio(this.getOdio() - valor);
        return String.format("O ódio atual de %s é %d", this.getNome(), this.getOdio());
    }
}
