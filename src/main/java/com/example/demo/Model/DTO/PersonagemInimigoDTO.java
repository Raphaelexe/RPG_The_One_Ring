package com.example.demo.Model.DTO;

import com.example.demo.Model.Arma;

import java.util.List;

public record PersonagemInimigoDTO(String nome,
                                   int resistencia,
                                   int bloqueio,
                                   int protecao,
                                   List<Integer> proficienciaDeCombate,
                                   List<String> armas,
                                   int poder,
                                   int odio,
                                   String habilidadeMortal) {}
