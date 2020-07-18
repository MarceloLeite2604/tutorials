package br.com.alura.escolalura.escolalura.models;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aluno {

    private ObjectId id;

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private Curso curso;

    private List<Nota> notas;

    private List<Habilidade> habilidades;

    private Contato contato;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Nota> getNotas() {
        if (notas == null) {
            notas = new ArrayList<>();
        }
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Habilidade> getHabilidades() {
        if (habilidades == null) {
            habilidades = new ArrayList<>();
        }
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public Aluno criarId() {
        setId(new ObjectId());
        return this;
    }

    public void adicionarHabilidade(Habilidade habilidade) {
        getHabilidades().add(habilidade);
    }

    public void adicionarNota(Nota nota) {
        getNotas().add(nota);
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
