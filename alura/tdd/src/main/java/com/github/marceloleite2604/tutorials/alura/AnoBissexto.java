package com.github.marceloleite2604.tutorials.alura;

public class AnoBissexto {

    public boolean ehBissexto(int ano) {
        if ( ano % 400 == 0) {
            return true;
        } else if (ano % 100 == 0 ) {
            return false;
        } else return ano % 4 == 0;
    }
}
