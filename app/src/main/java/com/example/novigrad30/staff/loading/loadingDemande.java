package com.example.novigrad30.staff.loading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.novigrad30.*;
import com.example.novigrad30.Class.Demande.Demande;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.administrator.functions.GererSuccursale;
import com.example.novigrad30.administrator.loading.loadingGestionSuccursale;
import com.example.novigrad30.staff.PortailSuccursale;
import com.example.novigrad30.staff.functions.VoirDemandes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loadingDemande extends AppCompatActivity {

    private static List<Demande> listeDesDemandes;
    DatabaseReference DemandeDB;
    private static EmployeHelperClass currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_demande);

        currentUser = PortailSuccursale.getCurrentUser();
        DemandeDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/"  + currentUser.getNomSuccursale() + "/Demandes" );

        listeDesDemandes = new ArrayList<>();
        Handler handler = new Handler();
        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), VoirDemandes.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DemandeDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    Demande demande = serviceSnap.getValue(Demande.class);
                    Boolean verify = false;

                    for (short i = 0; i < listeDesDemandes.size(); i++){
                        if (demande == listeDesDemandes.get(i))
                            verify = true;
                    }
                    if (!verify)
                        listeDesDemandes.add(demande);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public static List<Demande> getListeDesDemandes() {
        return listeDesDemandes;
    }

    public static EmployeHelperClass getCurrentUser() {
        return currentUser;
    }

}