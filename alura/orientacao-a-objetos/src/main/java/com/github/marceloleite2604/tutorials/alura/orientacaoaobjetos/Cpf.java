package com.github.marceloleite2604.tutorials.alura.orientacaoaobjetos;

public class Cpf implements Documento {
    private String valor;

    public Cpf(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Cpf)) {
            return false;
        }
        Cpf outro = (Cpf) o;
        return this.valor.equals(outro.valor);
    }

    public int hashCode() {
        return this.valor.hashCode();
    }

    public boolean ehValido() {
        return primeiroDigitoVerificadoEstaCorreto()
                && segundoDigitoVerificadorEstaCorreto();
    }

    private boolean primeiroDigitoVerificadoEstaCorreto() {
        // Calcula o primeiro digito verificador do CPF se
        // ele estiver correto e compara com o valor preenchido
        return true;
    }

    private boolean segundoDigitoVerificadorEstaCorreto() {
        // Calcula o segundo digito verificador do CPF se
        // ele estiver correto e compara com o valor preenchido
        return true;
    }
}
