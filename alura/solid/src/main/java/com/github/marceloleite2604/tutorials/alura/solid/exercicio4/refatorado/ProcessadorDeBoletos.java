package com.github.marceloleite2604.tutorials.alura.solid.exercicio4.refatorado;

import java.util.List;

public class ProcessadorDeBoletos {

    public void processa(List<Boleto> boletos, Fatura fatura) {

        for(Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValor(), MeioDePagamento.BOLETO);
            fatura.addPagamento(pagamento);
        }
    }
}
