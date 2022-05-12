package br.univates.appddm2022a.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cliente {

    private int id;
    private String nome;


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
}