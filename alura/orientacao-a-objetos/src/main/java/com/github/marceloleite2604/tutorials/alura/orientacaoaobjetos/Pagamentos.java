package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.util.*;
import java.util.function.Consumer;

public class Pagamentos implements Iterable<Pagamento> {

    private Collection<Pagamento> pagamentos = new ArrayList<>();
    private double valorPago;

    public Collection<Pagamento> pagamentosAntesDe(Calendar data) {
        Collection<Pagamento> pagamentosFiltrados = new ArrayList<>();
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getData()
                    .before(data)) {
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }

    public Collection<Pagamento> pagamentosComValorMaiorQue(double valorMinimo) {
        Collection<Pagamento> pagamentosFiltrados = new ArrayList<>();
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getValor() > valorMinimo) {
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }

    public Collection<Pagamento> pagamentosDo(String cnpjPagador) {
        Collection<Pagamento> pagamentosFiltrados = new ArrayList<>();
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getDocumentoPagador()
                    .equals(cnpjPagador)) {
                pagamentosFiltrados.add(pagamento);
            }
        }
        return pagamentosFiltrados;
    }

    public void registra(Pagamento pagamento) {
        pagamentos.add(pagamento);
        paga(pagamento.getValor());
    }

    public double getValorPago() {
        return this.valorPago;
    }

    private void paga(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor invalido para pagamento");
        }
        if (valor > 100) {
            valor = valor - 8;
        }
        this.valorPago += valor;
    }

    public boolean  foiRealizado(Pagamento pagamento) {
        return pagamentos.contains(pagamento);
    }

    @Override
    public Iterator<Pagamento> iterator() {
        return pagamentos.iterator();
    }

    @Override
    public void forEach(Consumer<? super Pagamento> action) {
        pagamentos.forEach(action);
    }

    @Override
    public Spliterator<Pagamento> spliterator() {
        return pagamentos.spliterator();
    }
}
