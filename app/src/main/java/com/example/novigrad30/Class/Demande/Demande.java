package com.example.novigrad30.Class.Demande;

public class Demande {
    String Nom_client;
    String Documents_Requis;
    String Etat;
    String formulaires_fournis;
    String formulaires_requis;
    String Service_demande;
    String courriel_du_client;
    String jour_de_depot;

    public Demande(String nom_client, String documents_Requis, String etat, String formulaires_fournis, String formulaires_requis, String service_demande, String courriel_du_client, String jour_de_depot) {
        Nom_client = nom_client;
        Documents_Requis = documents_Requis;
        Etat = etat;
        this.formulaires_fournis = formulaires_fournis;
        this.formulaires_requis = formulaires_requis;
        Service_demande = service_demande;
        this.courriel_du_client = courriel_du_client;
        this.jour_de_depot = jour_de_depot;
    }

    public Demande() {
    }

    public String getNom_client() {
        return Nom_client;
    }

    public void setNom_client(String nom_client) {
        Nom_client = nom_client;
    }

    public String getDocuments_Requis() {
        return Documents_Requis;
    }

    public void setDocuments_Requis(String documents_Requis) {
        Documents_Requis = documents_Requis;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public String getFormulaires_fournis() {
        return formulaires_fournis;
    }

    public void setFormulaires_fournis(String formulaires_fournis) {
        this.formulaires_fournis = formulaires_fournis;
    }

    public String getFormulaires_requis() {
        return formulaires_requis;
    }

    public void setFormulaires_requis(String formulaires_requis) {
        this.formulaires_requis = formulaires_requis;
    }

    public String getService_demande() {
        return Service_demande;
    }

    public void setService_demande(String service_demande) {
        Service_demande = service_demande;
    }

    public String getCourriel_du_client() {
        return courriel_du_client;
    }

    public void setCourriel_du_client(String courriel_du_client) {
        this.courriel_du_client = courriel_du_client;
    }

    public String getJour_de_depot() {
        return jour_de_depot;
    }

    public void setJour_de_depot(String jour_de_depot) {
        this.jour_de_depot = jour_de_depot;
    }
}
