package com.github.marceloleite2604.tutorials.alura.solid.exercicio3.refatorado;

public class Correios implements Frete {

    public double para(String cidade) {
        if("SAO PAULO".equals(cidade.toUpperCase())) {
            return 15;
        }
        return 30;
    }
}