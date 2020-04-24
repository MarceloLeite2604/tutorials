package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public class GerenciadorDeDividas {

    public void efetuarPagamento(Divida divida, String nomePagador, Documento documentoPagador, double valor) {
        Pagamento pagamento = new Pagamento();
        pagamento.setDocumentoPagador(documentoPagador);
        pagamento.setPagador(nomePagador);
        pagamento.setValor(valor);
        divida.registra(pagamento);
    }
}
