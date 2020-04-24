package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public class MinhaAplicacao {
    public static void main(String[] args) {
        //ArmazenadorDeDividas bd = new BancoDeDados("servidor", "usuario", "senha");
        ArmazenadorDeDividas arquivo = new ArquivoDeDividas("dividas.txt");
        BalancoEmpresa balanco = new BalancoEmpresa(arquivo);
        registraDividas(balanco);
        realizaPagamentos(balanco);
        //bd.desconecta();
    }

    private static void registraDividas(BalancoEmpresa balanco) {
        Divida d1 = new Divida();
        Divida d2 = new Divida();
        // preenche dados das dividas
        balanco.registraDivida(d1);
        balanco.registraDivida(d2);
    }

    private static void realizaPagamentos(BalancoEmpresa balanco) {
        Pagamento p1 = new Pagamento();
        Pagamento p2 = new Pagamento();
        Cnpj credor = new Cnpj("00.000.000/0001-01");
        // preenche dados dos pagamentos
        balanco.pagaDivida(credor, p1);
        balanco.pagaDivida(credor, p2);
    }
}