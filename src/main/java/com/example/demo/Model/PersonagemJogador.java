package com.example.demo.Model;


import com.example.demo.Model.Enums.PadraoDeVida;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.*;

@Entity
@Table
@Getter
@Setter
public class PersonagemJogador extends Personagem{
    private String nomeDoJogador;

    private String culturaHeroica;

    private int idade;

    @Enumerated(EnumType.STRING)
    private PadraoDeVida padraoDeVida;

    private int tesouro;

    private String caracteristicasNotaveis;

    private String bencaoCultural;

    private String patrono;

    private String chamado;

    private String caminhoDasSombras;

    private String falhas;

    private int bloqueioAtual;

//

    private int nivelForca;
    private int naForca;


    private int fascinio;
    private int atletismo;
    private int vigilancia;
    private int cacada;
    private int musica;
    private int oficio;

//

    private int nivelCoracao;
    private int naCoracao;
    private int esperanca;


    private int inducao;
    private int viagem;
    private int discernimento;
    private int cura;
    private int cortesia;
    private int batalha;

//

    private int nivelAstucia;
    private int naAstucia;


    private int persuasao;
    private int furtividade;
    private int busca;
    private int exploracao;
    private int enigma;
    private int historia;

//

    @ManyToMany
    @JoinTable(
            name = "personagem_jogador_equipamento",
            joinColumns = @JoinColumn(name = "personagem_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<Equipamento> equipamentos;

    private String recompensa;
    private int valor;

    private String virtudes;
    private int sabedoria;

//
    private int pontosDeAventura;

    private int pontosDePericia;

    private int pontosDeSociedade;

    private int cargaAtual;

    private int carga;

    private int fadiga;

    private int esperancaAtual;

    private int sombra;

    private int cicatrizesDeSombra;

    private boolean exausto;

    private boolean arrasado;

    private boolean ferido;

    private int ferimento;

    private String equipamentoDeViagem;

    public  PersonagemJogador(){}


    public void setProfCombate(List<Integer> proficienciaDeCombate){
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

    public void setForca(int nivelForca){
        this.nivelForca = nivelForca;
        this.naForca = 20 - nivelForca;
    }

    public void setListaForca(List<Integer> listaForca){
        if(listaForca != null && listaForca.size()==6){
            this.fascinio = listaForca.get(0);
            this.atletismo = listaForca.get(1);
            this.vigilancia = listaForca.get(2);
            this.cacada = listaForca.get(3);
            this.musica = listaForca.get(4);
            this.oficio = listaForca.get(5);
        }
        else {
            throw new IllegalArgumentException("O vetor de atributos de Força deve ter 6 entradas");
        }
    }

    public void setAstucia(int nivelAstucia){
        this.nivelAstucia = nivelAstucia;
        this.naAstucia = 20 - nivelAstucia;
    }

    public void setListaAstucia(List<Integer> listaAstucia){
        if(listaAstucia != null && listaAstucia.size()==6){
            this.inducao = listaAstucia.get(0);
            this.viagem = listaAstucia.get(1);
            this.discernimento = listaAstucia.get(2);
            this.cura = listaAstucia.get(3);
            this.cortesia = listaAstucia.get(4);
            this.batalha = listaAstucia.get(5);
        }
        else {
            throw new IllegalArgumentException("O vetor de atributos de Astucia deve ter 6 entradas");
        }
    }

    public void setCoracao(int nivelCoracao) {
        this.nivelCoracao = nivelCoracao;
        this.naCoracao = 20 - nivelCoracao;
    }

    public  void setListaCoracao(List<Integer> listaCoracao){

        if(listaCoracao != null && listaCoracao.size()==6){
            this.persuasao = listaCoracao.get(0);
            this.furtividade = listaCoracao.get(1);
            this.busca = listaCoracao.get(2);
            this.exploracao = listaCoracao.get(3);
            this.enigma = listaCoracao.get(4);
            this.historia = listaCoracao.get(5);
        }
        else {
            throw new IllegalArgumentException("O vetor de atributos de Coração deve ter 6 entradas");
        }

    }

    public void setEquipamentos(List<Equipamento> equipamentos){
        this.equipamentos = equipamentos;
        int varcarga = 0;
        int varbloqueio = 0;
        int varprotercao = 0;
        for(Equipamento equipamento: equipamentos){
            varcarga = varcarga + equipamento.getCarga();
            if(equipamento instanceof Armadura){
                varbloqueio = varbloqueio + ((Armadura) equipamento).getBloqueio();
                varprotercao = varprotercao + ((Armadura) equipamento).getProtecao();
            }
        }
        this.carga = varcarga;
        this.cargaAtual = this.resistencia - carga -this.fadiga;
        this.bloqueioAtual = varbloqueio + this.bloqueio;
        this.protecao = varprotercao;

    }

    @Override
    public void setResistencia(int resistencia){
        this.resistencia = resistencia;
        this.cargaAtual = resistencia - this.carga - this.fadiga;
    }

    public void setFadiga(int fadiga){
        this.fadiga = fadiga;
        this.cargaAtual = this.resistencia - this.carga - fadiga;
    }

    @Override
    public void setBloqueio(int bloqueio){
        this.bloqueio = bloqueio;

        int varbloqueio = 0;
        for(Equipamento equipamento: this.equipamentos){
            if(equipamento instanceof Armadura){
                varbloqueio = varbloqueio + ((Armadura) equipamento).getBloqueio();
            }
        }

        this.bloqueioAtual= bloqueio + varbloqueio;
    }

    public void setEsperanca(int esperanca){
        this.esperanca = esperanca;
        this.esperancaAtual= esperanca - this.sombra;
    }

    public void setSombra(int sombra){
        this.sombra = sombra;
        this.esperancaAtual= this.esperanca - sombra;
    }


    public String receberDano(int dano) {
        this.setFadiga(dano + this.fadiga);
        if(cargaAtual <= 0){
            this.setExausto(true);
            return String.format("A carga atual de %s é %d e o personagem está exausto", this.getNome(), this.getCargaAtual());
        }
        else{
        return String.format("A carga atual de %s é %d", this.getNome(), this.getCargaAtual());}
    }

    public String ferimento(int dado) {
        if(ferido){
            this.setFadiga(this.getResistencia()-this.getCarga());
            return String.format("Esse foi o segundo ferimento, %s está inconsciente e morendo", this.getNome());
        }
        else if (dado == 12){
            return String.format(" %s não se feriu", this.getNome());
        } else if (dado == 11) {
            this.setFadiga(this.getResistencia()-this.getCarga());
            return String.format(" %s está inconsciente e morendo", this.getNome());
        }
        else{
            this.setFerido(true);
            this.setFerimento(dado);
            return String.format("Esse foi o primeiro ferimento, %s ficará %d dias ", this.getNome(),dado);
        }
    }

}
