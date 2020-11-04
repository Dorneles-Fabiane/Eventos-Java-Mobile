package com.example.eventos_java_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public final class Local implements Serializable {

    private int id;
    private String descricao;
    private String bairro;
    private String cidade;
    private int capacidadePublico;

    public Local (int id, String descricao, String bairro, String cidade, int capacidadePublico) {
        this.id = id;
        this.descricao = descricao;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidadePublico = capacidadePublico;
    }


    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id= id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @NonNull
    @Override
    public String toString() {
        return this.id + " - " + this.descricao;
    }


}
