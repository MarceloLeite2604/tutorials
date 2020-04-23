package com.github.marceloleite2604.tutorials.alura.servico;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltroDeLancesTest {

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 2000),
                new Lance(joao, 1000),
                new Lance(joao, 3000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0)
                .getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 600),
                new Lance(joao, 500),
                new Lance(joao, 700),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0)
                .getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 3100),
                new Lance(joao, 4000),
                new Lance(joao, 5100)));

        assertEquals(1, resultado.size());
        assertEquals(5100, resultado.get(0)
                .getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre1000E3000EMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 1000),
                new Lance(joao, 2000),
                new Lance(joao, 3000),
                new Lance(joao, 3100),
                new Lance(joao, 4000),
                new Lance(joao, 5100)));

        assertEquals(2, resultado.size());
        assertEquals(2000, resultado.get(0)
                .getValor(), 0.00001);
        assertEquals(5100, resultado.get(1)
                .getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700EMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 500),
                new Lance(joao, 600),
                new Lance(joao, 700),
                new Lance(joao, 3100),
                new Lance(joao, 4000),
                new Lance(joao, 5100)));

        assertEquals(2, resultado.size());
        assertEquals(600, resultado.get(0)
                .getValor(), 0.00001);
        assertEquals(5100, resultado.get(1)
                .getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700Entre1000E3000EMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 500),
                new Lance(joao, 600),
                new Lance(joao, 700),
                new Lance(joao, 800),
                new Lance(joao, 1000),
                new Lance(joao, 2000),
                new Lance(joao, 3000),
                new Lance(joao, 3100),
                new Lance(joao, 4000),
                new Lance(joao, 5100)));

        assertEquals(3, resultado.size());
        assertEquals(600, resultado.get(0)
                .getValor(), 0.00001);
        assertEquals(2000, resultado.get(1)
                .getValor(), 0.00001);
        assertEquals(5100, resultado.get(2)
                .getValor(), 0.00001);
    }
}