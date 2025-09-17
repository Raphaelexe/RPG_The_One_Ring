package com.example.demo.repository;

import com.example.demo.Model.Arma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArmaRepositoryTest {

    @Autowired
    ArmaRepository repository;

    @Test
    public void salvarArmaTest(){
        Arma arma= new Arma();

        arma.setDano(12);
        arma.setNome("Arco");

        var armaSalva = repository.save(arma);
        System.out.println("Arma Salvo: " + armaSalva);
    }

}