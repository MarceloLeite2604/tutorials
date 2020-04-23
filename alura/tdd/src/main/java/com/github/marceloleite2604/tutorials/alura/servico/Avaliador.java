package com.github.marceloleite2604.tutorials.alura.servico;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double valorMedio = 0.0;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {

        if(leilao.getLances().size() == 0) {
            throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
        }

        double valorTotal = 0.0;
        for (Lance lance : leilao.getLances()) {

            if (lance.getValor() <= 0 ) {
                throw new IllegalArgumentException();
            }

            if (lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            if (lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
            valorTotal += lance.getValor();
        }
        if (!leilao.getLances()
                .isEmpty()) {
            valorMedio = valorTotal / (double) leilao.getLances()
                    .size();
        }
        pegaOsMaioresNo(leilao);
    }

    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort(Comparator.comparingDouble(Lance::getValor)
                .reversed());
        maiores = maiores.subList(0, Math.min(maiores.size(), 3));
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }

    public double getValorMedio() {
        return valorMedio;
    }

    public List<Lance> getMaiores() {
        return maiores;
    }
}
