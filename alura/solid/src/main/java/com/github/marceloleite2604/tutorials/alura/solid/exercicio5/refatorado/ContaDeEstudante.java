package com.github.marceloleite2604.tutorials.alura.solid.exercicio5.refatorado;

public class ContaDeEstudante implements Conta {

    private final ManipuladorDeContas manipuladorDeContas;
    private int milhas;

    public ContaDeEstudante() {
        this.manipuladorDeContas = new ManipuladorDeContas();
    }

    @Override
    public double getSaldo() {
        return manipuladorDeContas.getSaldo();
    }

    public void deposita(double valor) {
        manipuladorDeContas.deposita(valor);
        milhas += (int) valor;
    }

    @Override
    public void saca(double valor) {
        manipuladorDeContas.saca(valor);
    }

    public int getMilhas() {
        return milhas;
    }
}
