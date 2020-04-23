package com.github.marceloleite2604.tutorials.alura.dao;

import com.github.marceloleite2604.tutorials.alura.builder.LeilaoBuilder;
import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import com.github.marceloleite2604.tutorials.alura.dominio.Leilao;
import com.github.marceloleite2604.tutorials.alura.dominio.Usuario;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LeilaoDaoTest {

    private Session session;
    private LeilaoDao leilaoDao;
    private UsuarioDao usuarioDao;
    private LanceDao lanceDao;

    @Before
    public void antes() {
        session = new CriadorDeSessao().getSession();
        leilaoDao = new LeilaoDao(session);
        usuarioDao = new UsuarioDao(session);
        lanceDao = new LanceDao(session);

        // inicia transacao
        session.beginTransaction();
    }

    @After
    public void depois() {
        // faz o rollback
        session.getTransaction()
                .rollback();
        session.close();
    }

    @Test
    public void deveContarLeiloesNaoEncerrados() {
        // criamos um usuario
        Usuario mauricio =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        // criamos os dois leiloes
        Leilao ativo = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();

        Leilao encerrado = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .constroi();
        encerrado.encerra();

        // persistimos todos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(ativo);
        leilaoDao.salvar(encerrado);

        // invocamos a acao que queremos testar
        // pedimos o total para o DAO
        long total = leilaoDao.total();

        assertEquals(1L, total);
    }

    @Test
    public void deveRetornarZeroQuantoNaoHaLeiloesAbertos() {
        // criamos um usuario
        Usuario mauricio =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        // criamos os dois leiloes
        Leilao primeiroLeilaoEncerrado = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();
        primeiroLeilaoEncerrado.encerra();

        Leilao segundoLeilaoEncerrado = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();
        segundoLeilaoEncerrado.encerra();

        // persistimos todos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(primeiroLeilaoEncerrado);
        leilaoDao.salvar(segundoLeilaoEncerrado);

        // invocamos a acao que queremos testar
        // pedimos o total para o DAO
        long total = leilaoDao.total();

        assertEquals(0L, total);
    }

    @Test
    public void novosDeveRetornarSomenteLeilaoNovo() {
        // criamos um usuario
        Usuario mauricio =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        // criamos os dois leiloes
        Leilao novo = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();

        Leilao usado = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .usado()
                .constroi();

        // persistimos todos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(novo);
        leilaoDao.salvar(usado);

        List<Leilao> novos = leilaoDao.novos();

        assertEquals(1L, novos.size());
        assertEquals(novo, novos.get(0));
    }

    @Test
    public void antigosDeveRetornarSomenteLeiloesCriadosUmaSemanaAtras() {
        // criamos um usuario
        Usuario mauricio =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        Calendar umaSemanaAtras = Calendar.getInstance();
        umaSemanaAtras
                .add(Calendar.DAY_OF_MONTH, -10);

        Calendar hoje = Calendar.getInstance();

        // criamos os dois leiloes
        Leilao leilaoAntigo = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilaoNovo = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .usado()
                .constroi();
        leilaoAntigo.setDataAbertura(umaSemanaAtras);
        leilaoNovo.setDataAbertura(hoje);

        // persistimos todos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilaoAntigo);
        leilaoDao.salvar(leilaoNovo);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(1L, antigos.size());
        assertEquals(leilaoAntigo, antigos.get(0));
    }

    @Test
    public void antigosDeveRetornarSomenteLeiloesCriadosExatamenteUmaSemanaAtras() {
        // criamos um usuario
        Usuario mauricio =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        Calendar umaSemanaAtras = Calendar.getInstance();
        umaSemanaAtras
                .add(Calendar.DAY_OF_MONTH, -7);

        Calendar hoje = Calendar.getInstance();

        // criamos os dois leiloes
        Leilao leilaoAntigo = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilaoNovo = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .usado()
                .constroi();

        leilaoAntigo.setDataAbertura(umaSemanaAtras);
        leilaoNovo.setDataAbertura(hoje);

        // persistimos todos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilaoAntigo);
        leilaoDao.salvar(leilaoNovo);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(1L, antigos.size());
        assertEquals(leilaoAntigo, antigos.get(0));
    }

    @Test
    public void deveTrazerLeiloesNaoEncerradosNoPeriodo() {

        // criando as datas
        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);
        Calendar fimDoIntervalo = Calendar.getInstance();
        Calendar dataDoLeilao1 = Calendar.getInstance();
        dataDoLeilao1.add(Calendar.DAY_OF_MONTH, -2);
        Calendar dataDoLeilao2 = Calendar.getInstance();
        dataDoLeilao2.add(Calendar.DAY_OF_MONTH, -20);

        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        // criando os leiloes, cada um com uma data

        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .constroi();

        Leilao leilao2 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1500.0)
                .comDono(mauricio)
                .constroi();

        leilao1.setDataAbertura(dataDoLeilao1);
        leilao2.setDataAbertura(dataDoLeilao2);

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        // invocando o metodo para testar
        List<Leilao> leiloes =
                leilaoDao.porPeriodo(comecoDoIntervalo, fimDoIntervalo);

        // garantindo que a query funcionou
        assertEquals(1, leiloes.size());
        assertEquals("XBox", leiloes.get(0)
                .getNome());
    }

    @Test
    public void naoDeveTrazerLeiloesEncerradosNoPeriodo() {

        // criando as datas
        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);
        Calendar fimDoIntervalo = Calendar.getInstance();
        Calendar dataDoLeilao1 = Calendar.getInstance();
        dataDoLeilao1.add(Calendar.DAY_OF_MONTH, -2);

        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        // criando os leiloes, cada um com uma data
        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700)
                .comDono(mauricio)
                .constroi();
        leilao1.setDataAbertura(dataDoLeilao1);
        leilao1.encerra();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilao1);

        // invocando o metodo para testar
        List<Leilao> leiloes =
                leilaoDao.porPeriodo(comecoDoIntervalo, fimDoIntervalo);

        // garantindo que a query funcionou
        assertEquals(0, leiloes.size());
    }

    @Test
    public void disputadosEntreDeveRetornarSomenteLeiloesDentroDoIntervaloDeValorNaoEncerradosEComMaisDeTresLances() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");

        double valorInicial = 500.0;
        double valorFinal = 750.0;


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(600.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), joao, 614.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(valorInicial, valorFinal);

        assertEquals(1, leiloes.size());
        assertEquals(leilao3, leiloes.get(0));
    }

    @Test
    public void disputadosEntreNaoDeveRetornarLeiloesForaDoIntervaloDeValores() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");

        double valorInicial = 500.0;
        double valorFinal = 750.0;


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(480.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), joao, 614.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(valorInicial, valorFinal);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void disputadosEntreNaoDeveRetornarLeiloesComTresOuMenosLances() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");

        double valorInicial = 500.0;
        double valorFinal = 750.0;


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(600.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        leilaoDao.salvar(leilao4);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(valorInicial, valorFinal);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void disputadosEntreNaoDeveRetornarLeiloesEncerrados() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");

        double valorInicial = 500.0;
        double valorFinal = 750.0;


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(600.0)
                .comDono(mauricio)
                .encerrado()
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), joao, 614.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(valorInicial, valorFinal);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void disputadosEntreNaoDeveRetornarLeiloesComValoresForaDoIntervalo() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");

        double valorInicial = 500.0;
        double valorFinal = 750.0;


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(valorInicial - 1.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, valorInicial + 5.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, valorInicial + 10.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, valorInicial + 12.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), joao, valorInicial + 14.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Notebook")
                .comValor(valorFinal)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao4 = new Lance(Calendar.getInstance(), joao, valorInicial + 5.0, leilao3);
        Lance lance2Leilao4 = new Lance(Calendar.getInstance(), mateus, valorInicial + 10.0, leilao3);
        Lance lance3Leilao4 = new Lance(Calendar.getInstance(), joao, valorInicial + 12.0, leilao3);
        Lance lance4Leilao4 = new Lance(Calendar.getInstance(), joao, valorInicial + 14.0, leilao3);

        leilao3.adicionaLance(lance1Leilao4);
        leilao3.adicionaLance(lance2Leilao4);
        leilao3.adicionaLance(lance3Leilao4);
        leilao3.adicionaLance(lance4Leilao4);

        Leilao leilao5 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);
        lanceDao.salvar(lance1Leilao4);
        lanceDao.salvar(lance2Leilao4);
        lanceDao.salvar(lance3Leilao4);
        lanceDao.salvar(lance4Leilao4);
        leilaoDao.salvar(leilao5);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(valorInicial, valorFinal);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void testListaLeiloesDoUsuarioDeveRetornarSomenteLeiloesDoMesmoUsuario() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao1 = new Lance(Calendar.getInstance(), mateus, 605.0, leilao1);

        leilao1.adicionaLance(lance1Leilao1);


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(600.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), mateus, 614.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao4 = new Lance(Calendar.getInstance(), mateus, 1001.0, leilao3);

        leilao4.adicionaLance(lance1Leilao4);

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        lanceDao.salvar(lance1Leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);
        lanceDao.salvar(lance1Leilao4);

        List<Leilao> leiloes = leilaoDao.listaLeiloesDoUsuario(mateus);
        Set<Leilao> leiloesDistintos = new HashSet<>(leiloes);

        assertEquals(3, leiloesDistintos.size());

    }

    @Test
    public void getValorInicialMedioDoUsuario() {
        Usuario mauricio = new Usuario("Mauricio Aniche",
                "mauricio@aniche.com.br");

        Usuario joao = new Usuario("João Silva",
                "joao@silva.com.br");

        Usuario mateus = new Usuario("Mateus Albuquerque",
                "mateus@albuquerque.com.br");


        Leilao leilao1 = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao1 = new Lance(Calendar.getInstance(), mateus, 705.0, leilao1);

        leilao1.adicionaLance(lance1Leilao1);


        Leilao leilao2 = new LeilaoBuilder().comNome("TV 42\"")
                .comValor(300.0)
                .comDono(mauricio)
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Radio Stereo")
                .comValor(600.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao3 = new Lance(Calendar.getInstance(), joao, 605.0, leilao3);
        Lance lance2Leilao3 = new Lance(Calendar.getInstance(), mateus, 610.0, leilao3);
        Lance lance3Leilao3 = new Lance(Calendar.getInstance(), joao, 612.0, leilao3);
        Lance lance4Leilao3 = new Lance(Calendar.getInstance(), mateus, 614.0, leilao3);

        leilao3.adicionaLance(lance1Leilao3);
        leilao3.adicionaLance(lance2Leilao3);
        leilao3.adicionaLance(lance3Leilao3);
        leilao3.adicionaLance(lance4Leilao3);

        Leilao leilao4 = new LeilaoBuilder().comNome("Geladeira")
                .comValor(1000.0)
                .comDono(mauricio)
                .constroi();

        Lance lance1Leilao4 = new Lance(Calendar.getInstance(), mateus, 1001.0, leilao3);

        leilao4.adicionaLance(lance1Leilao4);

        // persistindo os objetos no banco
        usuarioDao.salvar(mauricio);
        usuarioDao.salvar(joao);
        usuarioDao.salvar(mateus);
        leilaoDao.salvar(leilao1);
        lanceDao.salvar(lance1Leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);
        lanceDao.salvar(lance1Leilao3);
        lanceDao.salvar(lance2Leilao3);
        lanceDao.salvar(lance3Leilao3);
        lanceDao.salvar(lance4Leilao3);
        leilaoDao.salvar(leilao4);
        lanceDao.salvar(lance1Leilao4);

        double valorInicialMedioDoUsuario = leilaoDao.getValorInicialMedioDoUsuario(mateus);

        assertEquals(767.0, valorInicialMedioDoUsuario, 0.01);
    }

    @Test
    public void deveDeletarUmLeilao() {
        Usuario usuario =
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        Leilao leilao = new LeilaoBuilder().comNome("XBox")
                .comValor(700.0)
                .comDono(usuario)
                .constroi();

        leilaoDao.salvar(leilao);
        leilaoDao.deletar(leilao);

        // envia tudo para o banco de dados
        session.flush();
        session.clear();

        Leilao leilaoNoBanco = leilaoDao.porId(leilao.getId());

        assertNull(leilaoNoBanco);

    }
}