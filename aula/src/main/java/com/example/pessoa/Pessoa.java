package com.example.pessoa;

public class Pessoa {
    private int idPessoa;

    private String nome;
    private String email;



    /*
     * Getters And Setters
     */
    public int getIdPessoa() {
        return idPessoa;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}

