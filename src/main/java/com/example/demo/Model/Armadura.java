package com.example.demo.Model;

import com.example.demo.Model.Enums.TipoArmadura;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "D")
public class Armadura extends Equipamento{

    private int bloqueio;

    private int protecao;

    @Enumerated(EnumType.STRING)
    private TipoArmadura tipo;
}
