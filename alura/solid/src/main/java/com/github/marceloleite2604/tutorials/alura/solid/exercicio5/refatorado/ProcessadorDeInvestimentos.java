package com.github.marceloleite2604.tutorials.alura.solid.exercicio5.refatorado;

import java.util.Arrays;
import java.util.List;

public class ProcessadorDeInvestimentos {

    public static void main(String[] args) {

        for (Conta conta : contasDoBanco()) {

            if (conta instanceof ContaComum) {
                ContaComum contaComum = (ContaComum)conta;
                contaComum.rende();

                System.out.println("Novo Saldo:");
                System.out.println(conta.getSaldo());
            }
        }
    }

    private static List<Conta> contasDoBanco() {
        return Arrays.asList(umaContaCom(100), umaContaCom(150), contaDeEstudanteCom(200));
    }

    private static Conta contaDeEstudanteCom(double amount) {
        ContaDeEstudante c = new ContaDeEstudante();
        c.deposita(amount);
        return c;
    }

    private static Conta umaContaCom(double valor) {
        ContaComum c = new ContaComum();
        c.deposita(valor);
        return c;
    }
}
