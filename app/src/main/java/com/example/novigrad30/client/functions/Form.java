package com.example.novigrad30.client.functions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.novigrad30.Class.Demande.Demande;
import com.example.novigrad30.R;

public class Form extends AppCompatActivity {

   public static EditText nom , prenom , date , adresse;
    Button suivant ;
    public static Demande demande = new Demande();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

      nom = (EditText)findViewById(R.id.et_client_name);
        prenom = (EditText)findViewById(R.id.et_client_firstname);
        date = (EditText)findViewById(R.id.editTextDate2);
        adresse = (EditText)findViewById(R.id.editTextTextPostalAddress);
        suivant = (Button)findViewById(R.id.button_docs);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // valider les informations
                //creer la nouvelle classe demande avec toutes les informations
                // valider les informations
                //creer la nouvelle classe demande avec toutes les informations
                demande.setEtat("En attente");
                demande.setDocuments_Requis(SelectionServicesClients.getChosen_one().getFormulaireRequis());
                demande.setNom_client(nom.getText().toString().trim()+" " + prenom.getText().toString().trim());
                demande.setService_demande(SelectionServicesClients.getChosen_one().getServiceName());
                demande.setFormulaires_requis("OK");
                //Allez dans l'activite suivante
                Intent docs = new Intent(getApplicationContext(), GettingDocs.class);
                startActivity(docs);

            }
        });
    }

    public static Demande get_demande(){ return demande;}

    public static String getData(){
        String data = "Nom : "+ nom.getText().toString().trim()+"/Prenom : "+prenom.getText().toString().trim()+"/Naissance :"+ date.getText().toString().trim();
        return data;}
}