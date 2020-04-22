package com.github.marceloleite2604.tutorials.alura.solid.exercicio5.refatorado;

public class ContaComum implements Conta {

    private final ManipuladorDeContas manipuladorDeContas;

    public ContaComum() {
        this.manipuladorDeContas = new ManipuladorDeContas();
    }

    public void deposita(double valor) {
        manipuladorDeContas.deposita(valor);
    }

    public void saca(double valor) {
        manipuladorDeContas.saca(valor);
    }

    public void rende() {
        manipuladorDeContas.deposita(manipuladorDeContas.getSaldo()*0.01);
    }

    public double getSaldo() {
        return manipuladorDeContas.getSaldo();
    }
}