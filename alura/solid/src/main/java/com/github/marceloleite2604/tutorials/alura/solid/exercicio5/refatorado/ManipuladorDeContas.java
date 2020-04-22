package com.github.marceloleite2604.tutorials.alura.solid.exercicio5.refatorado;

public class ManipuladorDeContas {
    protected double saldo;

    public ManipuladorDeContas() {
        this.saldo = 0;
    }

    public void deposita(double valor) {
        this.saldo += valor;
    }

    public void saca(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
