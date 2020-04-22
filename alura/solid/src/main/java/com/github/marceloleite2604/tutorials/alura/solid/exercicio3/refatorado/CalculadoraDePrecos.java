package com.github.marceloleite2604.tutorials.alura.solid.exercicio3.refatorado;

public class CalculadoraDePrecos {

    private final TabelaDePreco tabelaDePreco;

    private final Frete frete;

    public CalculadoraDePrecos(TabelaDePreco tabelaDePreco, Frete frete) {
        this.tabelaDePreco = tabelaDePreco;
        this.frete = frete;
    }

    public double calcula(Compra produto) {

        double desconto = tabelaDePreco.descontoPara(produto.getValor());
        double custoFrete = frete.para(produto.getCidade());

        return produto.getValor() * (1-desconto) + custoFrete;
    }
}

