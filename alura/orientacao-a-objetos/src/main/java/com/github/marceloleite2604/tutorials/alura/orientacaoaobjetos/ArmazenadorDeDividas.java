package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public interface ArmazenadorDeDividas {
    void salva(Divida divida);
    Divida carrega(Documento documentoCredor);
}