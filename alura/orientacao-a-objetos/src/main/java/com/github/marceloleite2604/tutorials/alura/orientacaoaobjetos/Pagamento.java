package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.util.Calendar;

public class Pagamento {
    private String pagador;
    private Documento documentoPagador;
    private double valor;
    private Calendar data;
    private Movimentacao movimentacao;

    public String getPagador() {
        return this.pagador;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public Documento getDocumentoPagador() {
        return this.documentoPagador;
    }

    public void setDocumentoPagador(Documento documentoPagador) {
        this.documentoPagador = documentoPagador;
    }

    public double getValor() {
        return this.valor;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return this.data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public double getEncargos() {
        return movimentacao.getEncargos();
    }
}