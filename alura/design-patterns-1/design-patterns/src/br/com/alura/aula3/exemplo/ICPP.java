package br.com.alura.aula3.exemplo;

import br.com.alura.aula1.Imposto;
import br.com.alura.aula1.Orcamento;

class ICPP implements Imposto {
    public double calcula(Orcamento orcamento) {
      if(orcamento.getValor() > 500) {
        return orcamento.getValor() * 0.07;
      } else {
        return orcamento.getValor() * 0.05;
      }
    }
}
