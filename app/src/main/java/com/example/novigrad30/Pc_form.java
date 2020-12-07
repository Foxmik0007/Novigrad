package com.example.novigrad30;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Pc_form extends AppCompatActivity {

   public static EditText nom_pc , prenom_pc , date_pc , adresse_pc, choix;
    Button suivant_pc ;
   public static Demande demande = new Demande();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_form);
       nom_pc = (EditText)findViewById(R.id.et_client_name_pc);
        prenom_pc = (EditText)findViewById(R.id.et_client_firstname_pc);
        date_pc = (EditText)findViewById(R.id.editTextDate2_pc);
        adresse_pc = (EditText)findViewById(R.id.editTextTextPostalAddress_pc);
        suivant_pc = (Button)findViewById(R.id.button_docs_pc);

        suivant_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // valider les informations
                //creer la nouvelle classe demande avec toutes les informations
                demande.setEtat("En attente");
                demande.setDocuments_Requis(SelectionServicesClients.getChosen_one().getFormulaireRequis());
                demande.setNom_client(nom_pc.getText().toString().trim()+" " + prenom_pc.getText().toString().trim());
                demande.setService_demande(SelectionServicesClients.getChosen_one().serviceName);
                demande.setFormulaires_requis("OK");
                //Allez dans l'activite suivante
                Intent docs = new Intent(getApplicationContext(),GettingDocs.class);
               startActivity(docs);

            }
        });
    }

    public static Demande get_demande(){ return demande;}
    public static String getData(){
        String data = "Nom : "+ nom_pc.getText().toString().trim()+"/Prenom : "+prenom_pc.getText().toString().trim()+"/Naissance :"+ date_pc.getText().toString().trim()+"/Permis Type : "+choix.getText().toString().trim();
        return data;}
}