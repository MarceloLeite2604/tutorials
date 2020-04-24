package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

import java.util.Objects;

public class Cnpj implements Documento {

    private final String valor;

    public Cnpj(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    public boolean ehValido() {
        return primeiroDigitoVerificador() == primeiroDigitoCorreto()
                && segundoDigitoVerificador() == segundoDigitoCorreto();
    }
    private int primeiroDigitoVerificador() {
        // Extrai o primeiro dígito verificador do CNPJ armazenado
        // no atributo valor
        return 0;
    }
    private int primeiroDigitoCorreto() {
        // Calcula o primeiro dígito verificador correto para
        // o CNPJ armazenado no atributo valor
        return 0;
    }
    private int segundoDigitoVerificador() {
        // Extrai o segundo dígito verificador do CNPJ armazenado
        // no atributo valor
        return 0;
    }
    private int segundoDigitoCorreto() {
        // Calcula o segundo dígito verificador correto para
        // o CNPJ armazenado no atributo valor
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj = (Cnpj) o;
        return Objects.equals(valor, cnpj.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
