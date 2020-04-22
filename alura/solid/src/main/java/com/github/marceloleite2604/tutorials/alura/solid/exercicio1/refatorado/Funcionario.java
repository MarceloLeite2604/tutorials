package com.github.marceloleite2604.tutorials.alura.solid.exercicio1.refatorado;

import java.util.Calendar;

public class Funcionario {

    private int id;
    private String nome;
    private Cargo cargo;
    private Calendar dataDeAdmissao;
    private double salarioBase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Calendar getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Calendar dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

}