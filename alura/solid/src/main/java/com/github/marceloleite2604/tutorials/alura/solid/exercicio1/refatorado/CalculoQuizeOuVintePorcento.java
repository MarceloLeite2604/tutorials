package com.github.marceloleite2604.tutorials.alura.solid.exercicio1.refatorado;

public class CalculoQuizeOuVintePorcento implements RegraDeCalculo {
    @Override
    public double calcular(Funcionario funcionario) {
        if (funcionario.getSalarioBase() > 3000.0) {
            return funcionario.getSalarioBase() * 0.8;
        } else {
            return funcionario.getSalarioBase() * 0.9;
        }
    }
}
