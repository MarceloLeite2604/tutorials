package com.github.marceloleite2604.tutorials.alura.solid.exercicio3.original;

public class Frete {
    public double para(String cidade) {
        if("SAO PAULO".equals(cidade.toUpperCase())) {
            return 15;
        }
        return 30;
    }
}