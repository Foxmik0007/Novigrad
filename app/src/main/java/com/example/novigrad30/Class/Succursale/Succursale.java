package com.example.novigrad30.Class.Succursale;

import com.example.novigrad30.Class.Service.ServicesHelperClass;

import java.util.ArrayList;
import java.util.List;

public class Succursale {
    private String nom;
    private String adresse;
    public List<ServicesHelperClass>serviceOfferts = new ArrayList<>();

    public Succursale(String nom, String adresse, List<ServicesHelperClass> serviceOfferts) {
        this.nom = nom;
        this.adresse = adresse;
        this.serviceOfferts = serviceOfferts;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<ServicesHelperClass> getServiceOfferts() {
        return serviceOfferts;
    }

    public void setServiceOfferts(List<ServicesHelperClass> serviceOfferts) {
        this.serviceOfferts = serviceOfferts;
    }
}
