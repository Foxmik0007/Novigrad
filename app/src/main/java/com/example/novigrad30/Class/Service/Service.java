package com.example.novigrad30.Class.Service;

public class Service {
    String documents;
    String serviceId;
    String serviceName;
    String formulaireRequis;


    public Service(){}
    public Service(String serviceId, String serviceName , String documents, String formulaireRequis)
    {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.documents = documents;
        this.formulaireRequis = formulaireRequis;
    }
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName() {
        this.serviceName= serviceName;
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
}


