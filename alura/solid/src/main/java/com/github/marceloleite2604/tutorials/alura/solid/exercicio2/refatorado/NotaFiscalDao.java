package com.github.marceloleite2604.tutorials.alura.solid.exercicio2.refatorado;

public class NotaFiscalDao implements AcaoAposGerarNotaFiscal {

    public void executar(NotaFiscal nf) {
        System.out.println("salva nf no banco");
    }
}
