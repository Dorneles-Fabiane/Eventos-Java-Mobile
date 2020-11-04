package com.example.eventos_java_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {

    private int id;
    private String nome;
    private String data;
    private Local local;

    public Evento(int id, String nome, String data, Local local) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    //SOBRESCRITA DATA FORMATO - CASO O LocalDate funcione, este método aplica um novo formato para a data.

   /*public void setData(String data) {
        String format = "dd/MM/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        this.data = LocalDate.parse(data, formatter);
    }*/

    @NonNull
    @Override
    public String toString() {
        return id + " - " + nome;
    }
}


