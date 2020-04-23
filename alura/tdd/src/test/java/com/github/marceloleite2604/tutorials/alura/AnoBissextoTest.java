package com.github.marceloleite2604.tutorials.alura;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnoBissextoTest {

    @Test
    public void ehAnoBissextoAnoMultiploDe400() {
        assertTrue(new AnoBissexto().ehBissexto(2000));
    }

    @Test
    public void naoEhAnoBissextoAnoMultiploDe100ENaoMultiploDe400() {
        assertFalse(new AnoBissexto().ehBissexto(1900));
    }

    @Test
    public void ehAnoBissextoAnoMultiploDe4() {
        assertTrue(new AnoBissexto().ehBissexto(2004));
    }
}