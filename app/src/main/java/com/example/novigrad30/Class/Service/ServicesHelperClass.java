package com.example.novigrad30.Class.Service;


public class ServicesHelperClass {
    String serviceId;
    String nom;
    String documents;
    String  formulaireRequis;

    public ServicesHelperClass() {
    }

    public ServicesHelperClass(String serviceId,String nom , String documents, String formulaireRequis) {
        this.serviceId = serviceId;
        this.nom = nom;
        this.documents = documents;
        this.formulaireRequis = formulaireRequis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getFormulaireRequis() {
        return formulaireRequis;
    }

    public void setFormulaireRequis(String formulaireRequis) {
        this.formulaireRequis = formulaireRequis;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
