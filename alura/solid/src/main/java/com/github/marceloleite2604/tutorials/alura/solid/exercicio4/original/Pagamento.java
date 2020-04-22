package com.github.marceloleite2604.tutorials.alura.solid.exercicio4.original;

public class Pagamento {

    private double valor;
    private MeioDePagamento forma;

    public Pagamento(double valor, MeioDePagamento forma) {
        this.valor = valor;
        this.forma = forma;
    }

    public double getValor() {
        return valor;
    }

    public MeioDePagamento getForma() {
        return forma;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pagamento)) {
            return false;
        }
        Pagamento outro = (Pagamento) obj;
        if (forma != outro.forma
                || Double.doubleToLongBits(valor) != Double
                .doubleToLongBits(outro.valor)) {
            return false;
        }

        return true;
    }

}
