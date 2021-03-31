package com.github.marceloleite2604.tutorials;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {



    @Test
    void division() {
        assertThat(24 / 4).isEqualTo(6);
    }

    @Test
    void divisionByZero() {
        int result = 24 / 0;
        throw new UnsupportedOperationException();
    }

}
