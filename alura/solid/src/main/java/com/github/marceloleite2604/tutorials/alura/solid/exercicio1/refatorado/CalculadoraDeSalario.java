package com.github.marceloleite2604.tutorials.alura.solid.exercicio1.refatorado;

public class CalculadoraDeSalario {

    public double calcula(Funcionario funcionario) {
        return funcionario.getCargo()
                .getRegraDeCalculo()
                .calcular(funcionario);
    }
}