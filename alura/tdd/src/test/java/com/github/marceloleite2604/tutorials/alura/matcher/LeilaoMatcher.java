package com.github.marceloleite2604.tutorials.alura.matcher;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

    private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }

    public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }

    public boolean matchesSafely(Leilao leilao) {
        return leilao.getLances()
                .contains(lance);
    }

    public void describeTo(Description description) {
        description.appendText("leilão com lance " + lance.getValor());
    }
}
