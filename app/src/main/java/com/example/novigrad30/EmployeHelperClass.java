package com.example.novigrad30;

public class EmployeHelperClass {
    private String name , ID,  email , password, nomSuccursale;

    public EmployeHelperClass(String ID, String name , String email , String password, String nomSuccursale) {
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nomSuccursale = nomSuccursale;
    }

    public EmployeHelperClass() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setLastName(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomSuccursale() {
        return nomSuccursale;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
