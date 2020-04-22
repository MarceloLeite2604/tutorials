package com.github.marceloleite2604.tutorials.alura.solid.exercicio2.refatorado;

public class EnviadorDeEmail implements AcaoAposGerarNotaFiscal {

    public void executar(NotaFiscal nf) {
        System.out.println("envia email da nf " + nf.getId());
    }
}
