package com.example.novigrad30.staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.novigrad30.staff.functions.AddServiceSuccursale;
import com.example.novigrad30.staff.functions.AjoutHeureSuccursale;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.MainActivity;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.staff.functions.SupprimerServiceSuccursale;
import com.example.novigrad30.staff.functions.VoirDemandes;
import com.example.novigrad30.staff.functions.VoirServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PortailSuccursale extends AppCompatActivity {

    private static EmployeHelperClass currentUser;

    private TextView afficher;
    private Button AjoutService;
    private Button HeureSelect;
    private Button supprimer;
    private Button VoirService;
    private Button voirDemande;
    private Button seDeconnecter;


    DatabaseReference ServiceDB;
    DatabaseReference currentDB;
    private static List<ServicesHelperClass> ServiceList;
    private static List<ServicesHelperClass> SuccursalServiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail_succursale);

        currentUser = MainActivity.getCurrentStaff();

        //Instantiation base de donnee
        ServiceList = new ArrayList<>();
        ServiceDB = FirebaseDatabase.getInstance().getReference("SERVICES");

        //Instantiation des bases de données
        SuccursalServiceList = new ArrayList<>();
        currentDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + currentUser.getNomSuccursale() + "/Services Offerts");
        SuccursalServiceList.clear();
        //Catching
        AjoutService = (Button) findViewById(R.id.btnChoisirServices);
        HeureSelect = (Button) findViewById(R.id.btnHoraires);
        supprimer = (Button)findViewById(R.id.btnSuprimerServicesSuccursale);
        VoirService = (Button) findViewById(R.id.btnVoirServices);
        voirDemande = (Button) findViewById(R.id.btnVoirDemandes);
        seDeconnecter = (Button) findViewById(R.id.exitSucc);
        afficher = (TextView) findViewById(R.id.textView13);

        afficher.setText(currentUser.getNomSuccursale());

        AjoutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddServiceSuccursale.class);
                startActivity(intent);
            }
        });

        HeureSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AjoutHeureSuccursale.class);
                startActivity(intent);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), SupprimerServiceSuccursale.class);
                startActivity(i2);
            }
        });
        VoirService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoirServices.class);
                startActivity(intent);
            }
        });

        voirDemande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoirDemandes.class);
                startActivity(intent);
            }
        });

        seDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = null;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


    //RECUPERATION DES DONNEES CONTENUES DANS LA BASE DE DONNEES
    protected void onStart() {
        super.onStart();

        //Creation d'une base locale
        ServiceDB.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < ServiceList.size(); i++){
                        if (service.getNom() == ServiceList.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        ServiceList.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

        //Creation d'une base locale
        currentDB.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < SuccursalServiceList.size(); i++){
                        if (service.getNom() == SuccursalServiceList.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        SuccursalServiceList.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
    public static EmployeHelperClass getCurrentUser (){
        return currentUser;
    }


    public static List<ServicesHelperClass> getServiceList() {
        return ServiceList;
    }

    public static List<ServicesHelperClass> getSuccursalServiceList() {
        return SuccursalServiceList;
    }
}