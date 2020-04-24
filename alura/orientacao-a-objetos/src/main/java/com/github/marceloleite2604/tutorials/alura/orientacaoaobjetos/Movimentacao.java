package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.util.Calendar;

public class Movimentacao {
    private double valor;
    private Conta conta;
    private Calendar data;

    public double getEncargos() {
        return valor * 0.01;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
}
