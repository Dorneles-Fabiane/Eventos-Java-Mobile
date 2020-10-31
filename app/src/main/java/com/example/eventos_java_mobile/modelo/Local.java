package com.example.eventos_java_mobile.modelo;

import java.io.Serializable;

public final class Local implements Serializable {

    private int id;
    private String nome;
    private String bairro;
    private String cidade;
    private int capacidadePublico;

    public Local (String nome, String bairro, String cidade, int capacidadePublico) {
        this.nome = nome;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidadePublico = capacidadePublico;
    }

    //verificar necessidade do segundo construtor
    public Local (String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id= id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidadePublico() {
        return capacidadePublico;
    }

    public void setCapacidadePublico(int capacidadePublico) {
        this.capacidadePublico = capacidadePublico;
    }

    @Override
    public String toString() {
        return "Local{" +
                "nome='" + nome + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", capacidadePublico=" + capacidadePublico +
                '}';
    }
}
