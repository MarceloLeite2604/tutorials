package com.github.marceloleite2604.tutorials.alura.solid.exercicio2.refatorado;

import java.util.List;

public class GeradorDeNotaFiscal {

    List<AcaoAposGerarNotaFiscal> acoesAposGerarNotaFiscal;

    public GeradorDeNotaFiscal(List<AcaoAposGerarNotaFiscal> acoesAposGerarNotaFiscal) {
        this.acoesAposGerarNotaFiscal = acoesAposGerarNotaFiscal;
    }

    public NotaFiscal gera(Fatura fatura) {

        double valor = fatura.getValorMensal();

        NotaFiscal notaFiscal = new NotaFiscal(valor, impostoSimplesSobreO(valor));

        for (AcaoAposGerarNotaFiscal acaoAposGerarNotaFiscal : acoesAposGerarNotaFiscal) {
            acaoAposGerarNotaFiscal.executar(notaFiscal);
        }

        return notaFiscal;
    }

    private double impostoSimplesSobreO(double valor) {
        return valor * 0.06;
    }
}
