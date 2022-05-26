package br.univates.appddm2022a.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cliente {

    private int id;
    private String nome;
    private String data;
    private String telefone;


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

    public String getData(){return data;}

    public void setData(String data){this.data = data;}

    public String getTelefone(){return telefone;}

    public void setTelefone(String telefone){this.telefone = telefone;}
}