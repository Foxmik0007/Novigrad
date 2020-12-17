package com.example.novigrad30.Class.Client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.novigrad30.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientRg extends AppCompatActivity {

    private EditText nom_client, prenom_client,pass_client,mail_client;
    private Button inscrire_client;

    DatabaseReference databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_rg);

        //CATCHING
        nom_client = (EditText)findViewById(R.id.etRecueillirNom);
        prenom_client = (EditText)findViewById(R.id.etRecueillirPrenom);
        pass_client = (EditText)findViewById(R.id.etRecueillirMotDePasse);
        mail_client = (EditText)findViewById(R.id.etRecueillirMail);

        inscrire_client = (Button)findViewById(R.id.register);


        inscrire_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseClient = FirebaseDatabase.getInstance().getReference("CLIENTS");
                String name = prenom_client.getText().toString().trim();
                String LastName  = nom_client.getText().toString().trim();
                String email = mail_client.getText().toString().trim();
                String password = pass_client.getText().toString().trim();

                //RECUPERATION D'UNE CLÉE
                String id = databaseClient.push().getKey();

                Client client = new Client(id,LastName,name,password,email);
                databaseClient.child(name).setValue(client);
               Toast.makeText(ClientRg.this, "Client Ajouté ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}