package com.github.marceloleite2604.tutorials.alura.matematica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatematicaMalucaTest {

    @Test
    public void contaMalucaValorMaiorQueTrinta() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valor = 40;

        int esperado = 160;

        int calulado = matematicaMaluca.contaMaluca(valor);

        assertEquals(esperado, calulado);
    }

    @Test
    public void contaMalucaValorMenorQueTrintaMaiorQueDez() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valor = 20;

        int esperado = 60;

        int calulado = matematicaMaluca.contaMaluca(valor);

        assertEquals(esperado, calulado);
    }

    @Test
    public void contaMalucaValorMenorQueDez() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int valor = 8;

        int esperado = 16;

        int calulado = matematicaMaluca.contaMaluca(valor);

        assertEquals(esperado, calulado);
    }

}