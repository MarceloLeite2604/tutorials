package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.text.NumberFormat;
import java.util.Locale;

public class RelatorioDeDivida {

    private final Divida divida;

    public RelatorioDeDivida(Divida divida) {
        this.divida = divida;
    }

    public void geraRelatorio(NumberFormat numberFormat) {
        System.out.println("Cnpj credor: " + divida.getDocumentoCredor());
        System.out.println("Credor: " + divida.getCredor());

        System.out.println("Valor da dívida: " + numberFormat.format(divida.getTotal()));
        System.out.println("Valor pago: " + numberFormat.format(divida.getValorPago()));
    }

    public static void main(String[] args) {
        Divida divida = new Divida();
        divida.setCredor("Uma empresa");
        divida.setTotal(100);
        divida.setDocumentoCredor(new Cnpj("00.000.001/0001-01"));

        Pagamento pagamento = new Pagamento();
        pagamento.setDocumentoPagador(new Cnpj("00.000.002/0002-02"));
        pagamento.setPagador("Outra empresa");
        pagamento.setValor(20);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        RelatorioDeDivida relatorioDeDivida = new RelatorioDeDivida(divida);
        relatorioDeDivida.geraRelatorio(numberFormat);
    }
}