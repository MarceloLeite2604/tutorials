package br.com.alura.escolalura.escolalura.models;

import java.util.List;

public class Contato {

    private String endereco;

    private List<Double> coordinates;

    private String type = "Point";

    public Contato() {
    }

    public Contato(String endereco, List<Double> coordinates) {
        this.endereco = endereco;
        this.coordinates = coordinates;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
