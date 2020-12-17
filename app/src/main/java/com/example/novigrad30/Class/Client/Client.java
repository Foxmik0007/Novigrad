package com.example.novigrad30.Class.Client;

public class Client {
    private String nom, prenom;
    private String id;
    private String password;
    private String mail;


    public Client(){};

    public Client(String id, String nom,String prenom, String passord,String mail ) {
        this.nom = nom;
        this.id = id;
        this.password = passord;
        this.mail = mail;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
