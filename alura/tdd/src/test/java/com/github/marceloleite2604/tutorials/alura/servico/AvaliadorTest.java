package com.github.marceloleite2604.tutorials.alura.servico;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;
import com.github.marceloleite2604.tutorials.alura.dominio.Usuario;
import com.github.marceloleite2604.tutorials.alura.testdatabuilders.CriadorDeLeilao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.marceloleite2604.tutorials.alura.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeEach
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        maria = new Usuario("Maria");
        joao = new Usuario("Joao");
        jose = new Usuario("José");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(maria, 250.0)
                .lance(joao, 300.0)
                .lance(jose, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    @DisplayName("Valor médio")
    public void getValorMedio() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(maria, 250.0)
                .lance(joao, 300.0)
                .lance(jose, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double valorMedioEsperado = 316.67;

        assertEquals(valorMedioEsperado, leiloeiro.getValorMedio(), 0.01);
    }

    @Test
    public void getValorMedioComZeroLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leiloeiro.avalia(leilao);

        double valorMedioEsperado = 0.0;

        assertEquals(valorMedioEsperado, leiloeiro.getValorMedio(), 0.01);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 1000.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComValoresAleatorios() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 200.0)
                .lance(jose, 450.0)
                .lance(joao, 120.0)
                .lance(jose, 700.0)
                .lance(joao, 630.0)
                .lance(jose, 230.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComValoresDescrescentes() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 400.0)
                .lance(jose, 300.0)
                .lance(joao, 200.0)
                .lance(jose, 100.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void getMaioresLeilaoComTresOuMaisLancesDeveRetornarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(jose, 200.0)
                .lance(joao, 300.0)
                .lance(jose, 400.0)
                .lance(joao, 500.0)
                .constroi();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getMaiores();

        assertEquals(3, maiores.size());
        assertEquals(500, maiores.get(0)
                .getValor());
        assertEquals(400, maiores.get(1)
                .getValor());
        assertEquals(300, maiores.get(2)
                .getValor());
    }

    @Test
    public void getMaioresLeilaoDoisLancesDeveRetornarDoisLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(jose, 200.0)
                .constroi();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0)
                .getValor());
        assertEquals(100, maiores.get(1)
                .getValor());
    }

    @Test
    public void getMaioresLeilaoSemLancesDeveRetornarListaVazia() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .constroi();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getMaiores();

        assertEquals(0, maiores.size());
    }

    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .constroi();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> leiloeiro.avalia(leilao));

        assertNotNull(exception);
    }

    @Test
    public void naoDeveAvaliarLeiloesComLanceZero() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, 0)
                .constroi();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> leiloeiro.avalia(leilao));

        assertNotNull(exception);
    }

    @Test
    public void naoDeveAvaliarLeiloesComLanceNegativo() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, -10)
                .constroi();

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> leiloeiro.avalia(leilao));

        assertNotNull(exception);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, 100)
                .lance(maria, 200)
                .lance(joao, 300)
                .lance(maria, 400)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getMaiores();
        assertEquals(3, maiores.size());

        assertThat(maiores, hasItems(
                new Lance(maria, 400),
                new Lance(joao, 300),
                new Lance(maria, 200)
        ));
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
}