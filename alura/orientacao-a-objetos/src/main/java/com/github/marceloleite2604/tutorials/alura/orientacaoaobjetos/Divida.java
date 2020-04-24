package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public class Divida {
    private double total;
    private String credor;
    private Documento documentoCredor;
    private Pagamentos pagamentos = new Pagamentos();
    private Movimentacao movimentacao;

    public boolean cnpjValido() {
        return primeiroDigitoVerificadorDoCnpj() == primeiroDigitoCorretoParaCnpj()
                && segundoDigitoVerificadorDoCnpj() == segundoDigitoCorretoParaCnpj();
    }

    public Documento getDocumentoCredor() {
        return this.documentoCredor;
    }

    public void setDocumentoCredor(Documento documentoCredor) {
        this.documentoCredor = documentoCredor;
    }

    public String getCredor() {
        return this.credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    private int primeiroDigitoCorretoParaCnpj() {
        // Calcula o primeiro dígito verificador correto para
        // o CNPJ armazenado no atributo valor
        return 0;
    }

    private int primeiroDigitoVerificadorDoCnpj() {
        // Extrai o primeiro dígito verificador do CNPJ armazenado
        // no atributo valor
        return 0;
    }

    private int segundoDigitoCorretoParaCnpj() {
        // Calcula o segundo dígito verificador correto para
        // o CNPJ armazenado no atributo valor
        return 0;
    }

    private int segundoDigitoVerificadorDoCnpj() {
        // Extrai o segundo dígito verificador do CNPJ armazenado
        // no atributo valor
        return 0;
    }

    public double valorAPagar() {
        return this.total - this.pagamentos.getValorPago();
    }

    public void registra(Pagamento pagamento) {
        pagamentos.registra(pagamento);
    }

    public double getValorPago() {
        return pagamentos.getValorPago();
    }
}
