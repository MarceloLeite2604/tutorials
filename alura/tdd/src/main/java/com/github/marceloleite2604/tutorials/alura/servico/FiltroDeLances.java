package com.github.marceloleite2604.tutorials.alura.servico;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;

import java.util.ArrayList;
import java.util.List;

public class FiltroDeLances {

    public List<Lance> filtra(List<Lance> lances) {
        ArrayList<Lance> resultado = new ArrayList<Lance>();

        for (Lance lance : lances) {
            if (lance.getValor() > 1000 && lance.getValor() < 3000)
                resultado.add(lance);
            else if (lance.getValor() > 500 && lance.getValor() < 700)
                resultado.add(lance);
            else if (lance.getValor() > 5000)
                resultado.add(lance);
        }

        return resultado;
    }
}