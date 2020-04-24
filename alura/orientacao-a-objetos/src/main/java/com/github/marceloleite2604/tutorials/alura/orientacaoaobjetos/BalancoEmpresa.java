package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public class BalancoEmpresa {
    private final ArmazenadorDeDividas armazenadorDeDividas;

    public BalancoEmpresa(ArmazenadorDeDividas armazenadorDeDividas) {
        this.armazenadorDeDividas = armazenadorDeDividas;
    }

    public void registraDivida(Divida divida) {
        armazenadorDeDividas.salva(divida);
    }

    public void pagaDivida(Documento documentoCredor, Pagamento pagamento) {
        Divida divida = armazenadorDeDividas.carrega(documentoCredor);
        if (divida != null) {
            divida.registra(pagamento);
        }
        armazenadorDeDividas.salva(divida);
    }
}