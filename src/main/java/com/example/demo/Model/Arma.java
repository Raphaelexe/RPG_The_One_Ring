package com.example.demo.Model;

import com.example.demo.Model.Enums.ProfDeCombateArma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Table
@Entity
@Getter
@Setter
@DiscriminatorValue(value = "A")
public class Arma extends Equipamento {

    private int dano;

    private int ferimento;

    @Enumerated(EnumType.STRING)
    private ProfDeCombateArma profDeCombateArma;

}
