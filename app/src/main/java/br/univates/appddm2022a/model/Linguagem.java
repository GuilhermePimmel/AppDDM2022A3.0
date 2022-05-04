package br.univates.appddm2022a.model;

public class Linguagem {

    private int id;
    private String nome;
    private String descricao;
    private int favorito;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFavorito(){
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString(){
        return nome;
    }
}