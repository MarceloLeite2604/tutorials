package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.io.File;

public class ArquivoDeDividas implements ArmazenadorDeDividas {
    private File arquivo;

    public ArquivoDeDividas(String nomeDoArquivo) {
        this.arquivo = new File(nomeDoArquivo);
    }

    @Override
    public Divida carrega(Documento documentoCredor) {
        return null;
    }

    @Override
    public void salva(Divida divida) {
    }
}