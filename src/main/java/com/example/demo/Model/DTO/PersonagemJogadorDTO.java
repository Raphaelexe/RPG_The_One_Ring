package com.example.demo.Model.DTO;

import com.example.demo.Model.Enums.PadraoDeVida;

import java.util.List;

public record PersonagemJogadorDTO(String nome,
                                   int resistencia,
                                   int bloqueio,
                                   int protecao,
                                   List<Integer> proficienciaDeCombate,
                                   List<String> equipamento,


                                   String nomeDoJogador,
                                   String culturaHeroica,
                                   int idade,
                                   PadraoDeVida padraoDeVida,
                                   int tesouro,
                                   String caracteristicasNotaveis,
                                   String bencaoCultural,
                                   String patrono,
                                   String chamado,
                                   String caminhoDasSombras,
                                   String falhas,

                                   int nivelForca,
                                   List<Integer> listaForca,

                                   int nivelCoracao,
                                   List<Integer> listaCoracao,

                                   int nivelAstucia,
                                   List<Integer> listaAstucia,

                                   String recompensa,
                                   int valor,

                                   String virtudes,
                                   int sabedoria,

                                   int pontosDeAventura,
                                   int pontosDePericia,
                                   int pontosDeSociedade,

                                   int fadiga,
                                   int sombra,

                                   boolean exausto,
                                   boolean arrasado,
                                   boolean ferido,
                                   int ferimento,

                                   String equipamentoDeViagem,
                                   int esperanca

                                   ) {
}
