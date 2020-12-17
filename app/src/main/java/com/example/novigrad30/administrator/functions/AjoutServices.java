package com.example.novigrad30.administrator.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AjoutServices extends AppCompatActivity {

    private EditText nom ;
    private EditText Liste;
    private EditText formulaire;
    private Button ajout;

    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_services);
        nom = (EditText) findViewById(R.id.etNomService);
        Liste = (EditText)findViewById(R.id.etListe);
        formulaire = (EditText)findViewById(R.id.etTypeDeFormulaire);
        ajout =  (Button)findViewById(R.id.btnAjout);
        databaseServices = FirebaseDatabase.getInstance().getReference("SERVICES");

        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

    }

    private void addService()
    {
        String name = nom.getText().toString().trim();
        String list = Liste.getText().toString().trim();
        String form = formulaire.getText().toString().trim();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(list) && !TextUtils.isEmpty(form))
        {
            String id = databaseServices.push().getKey();

            ServicesHelperClass service = new ServicesHelperClass(id,name,list,form);
            databaseServices.child(name).setValue(service);

            Toast.makeText(this, "Service defini ", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "Entrez des valeurs valides ", Toast.LENGTH_SHORT).show();
        }
    }
}