package com.github.marceloleite2604.tutorials.alura.testdatabuilders;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;
import com.github.marceloleite2604.tutorials.alura.dominio.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;

    public CriadorDeLeilao() { }

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() {
        return leilao;
    }
}
