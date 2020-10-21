package com.example.eventos_java_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {

    private int id;

    private String nome;
    //private LocalDate data;     //Não coloquei o "LocalDate" porque o aplicativo cai se aplico esse formato. Ao invés coloquei a DATA como STRING.
    private String data;
    private String local;

    public Evento(int id, String nome, String data, String local) {
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

    public Evento(String nome, String data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
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

    //SOBRESCRITA DATA FORMATO - CASO O LocalDate funcione, este método aplica um novo formato para a data.

   /*public void setData(String data) {
        String format = "dd/MM/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        this.data = LocalDate.parse(data, formatter);
    }*/

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + data;
    }
}


