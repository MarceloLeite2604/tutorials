package com.github.marceloleite2604.tutorials.alura.solid.exercicio4.refatorado;

import java.util.ArrayList;
import java.util.List;

public class Fatura {

    private final String cliente;
    private final double valor;
    private final List<Pagamento> pagamentos;
    private boolean pago;

    public Fatura(String cliente, double valor) {
        this.cliente = cliente;
        this.valor = valor;
        this.pagamentos = new ArrayList<>();
        this.pago = false;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public boolean isPago() {
        return pago;
    }

    public void addPagamento(Pagamento pagamento) {

        this.pagamentos.add(pagamento);
        checkFaturaPaga();
    }

    private void checkFaturaPaga() {
        if(calculaTotalPagamentos() >= valor) {
            this.pago = true;
        }
    }

    private double calculaTotalPagamentos() {
        double total = 0.0;

        for (Pagamento pagamento : pagamentos) {
            total += pagamento.getValor();
        }
        return total;
    }


}