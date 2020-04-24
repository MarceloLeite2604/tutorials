package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.util.ArrayList;

public class TestaPagamentos {

    public static void main(String[] args) {
        Pagamentos pagamentos = new Pagamentos();
        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(105);
        Pagamento pagamento2 = new Pagamento();
        pagamento2.setValor(25);

        pagamentos.registra(pagamento1);
        pagamentos.registra(pagamento2);

        System.out.println("Valor total pago: " + pagamentos.getValorPago());

        Iterable<Pagamento> colecao = new Pagamentos(); // ou HashSet<Pagamento>
        for (Pagamento pagamento : colecao) {
            System.out.println(pagamento.getValor());
        }

    }
}
