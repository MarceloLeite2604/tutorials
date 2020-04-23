package com.github.marceloleite2604.tutorials.alura.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
        if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLanceDado().getUsuario().equals(usuario)
                && qtdDeLancesDo(usuario) < 5;
    }

    private int qtdDeLancesDo(Usuario usuario) {
        int total = 0;
        for(Lance l : lances) {
            if(l.getUsuario().equals(usuario)) total++;
        }
        return total;
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }


    public void dobraLance(Usuario usuario) {
        Lance ultimoLance = ultimoLanceDado(usuario);

        if (!Objects.isNull(ultimoLance)) {
            Lance lanceDobrado = new Lance(usuario, ultimoLance.getValor() * 2.0);
            propoe(lanceDobrado);
        }
    }

    public Lance ultimoLanceDado(Usuario usuario) {
        Lance ultimoLance = null;
        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario)) {
                ultimoLance = lance;
            }
        }
        return ultimoLance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leilao leilao = (Leilao) o;
        return Objects.equals(descricao, leilao.descricao) &&
                Objects.equals(lances, leilao.lances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, lances);
    }
}
