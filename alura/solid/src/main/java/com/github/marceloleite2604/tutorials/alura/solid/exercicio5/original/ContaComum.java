package com.github.marceloleite2604.tutorials.alura.solid.exercicio5.original;

public class ContaComum {

    protected double saldo;

    public ContaComum() {
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

    public void rende() {
        this.saldo += this.saldo * 0.01;
    }

    public double getSaldo() {
        return saldo;
    }
}