package com.github.marceloleite2604.tutorials.alura.dao;

import com.github.marceloleite2604.tutorials.alura.dominio.Lance;
import org.hibernate.Session;

public class LanceDao {

    private final Session session;

    public LanceDao(Session session) {
        this.session = session;
    }

    public Lance porId(int id) {
        return (Lance) session.load(Lance.class, id);
    }

    public void salvar(Lance lance) {
        session.save(lance);
    }

    public void atualizar(Lance lance) {
        session.merge(lance);
    }

    public void deletar(Lance lance) {
        session.delete(lance);
    }
}
