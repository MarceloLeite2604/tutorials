package com.github.marceloleite2604.tutorials.alura.solid.exercicio1.refatorado;

public enum Cargo {
    DESENVOLVEDOR(new CalculoDezOuVintePorcento()),
    DBA(new CalculoQuizeOuVintePorcento()),
    TESTER(new CalculoQuizeOuVintePorcento());

    private final RegraDeCalculo regraDeCalculo;


    Cargo(RegraDeCalculo regraDeCalculo) {
        this.regraDeCalculo = regraDeCalculo;
    }

    public RegraDeCalculo getRegraDeCalculo() {
        return regraDeCalculo;
    }
}

