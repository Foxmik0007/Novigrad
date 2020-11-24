package com.example.novigrad30;

public class Heure {
    String Debut;
    String Fin;

    public Heure(){}

    public Heure(String Debut,String Fin) {
        this.Debut = Debut;
        this.Fin= Fin;
    }

    public String getDebut() {
        return Debut;
    }

    public void setDebut(String debut) {
        Debut = debut;
    }

    public String getFin() {
        return Fin;
    }

    public void setFin(String fin) {
        Fin = fin;
    }
}
