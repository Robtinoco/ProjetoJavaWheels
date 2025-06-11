package org.example.model;

public class Bicicleta {
    private String nome;
    private String modelo;
    private double precoHora;

    public Bicicleta(String nome, String modelo, double precoHora) {
        this.nome = nome;
        this.modelo = modelo;
        this.precoHora = precoHora;
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecoHora() {
        return precoHora;
    }

    @Override
    public String toString() {
        return nome + " (" + modelo + ") - R$" + precoHora + "/h";
    }
}

