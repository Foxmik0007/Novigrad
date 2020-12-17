package com.example.novigrad30.staff.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.widget.ListView;

import com.example.novigrad30.Class.Demande.Demande;
import com.example.novigrad30.staff.list.DemandeList;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.staff.PortailSuccursale;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoirDemandes extends AppCompatActivity {

    private static EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    DatabaseReference databaseDemande;
    ListView listViewDemande;
    List<Demande> listDemnades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_demandes);
        databaseDemande = FirebaseDatabase.getInstance().getReference("SUCCURSALES/"  + currentUser.getNomSuccursale() + "Demandes" );
        //getReference("SUCCURSALES").child("Succursale ottawa").child("Demandes").getRef()
        listDemnades = new ArrayList<>();

        listViewDemande = (ListView)findViewById(R.id.listViewDemandes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseDemande.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDemnades.clear();
                for(DataSnapshot demandeSnaphot : snapshot.getChildren())
                {
                    Demande demande = demandeSnaphot.getValue(Demande.class);
                    listDemnades.add(demande);
                }
                DemandeList adapter = new DemandeList(VoirDemandes.this,listDemnades);
                listViewDemande.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static EmployeHelperClass getCurrentUser() {
        return currentUser;
    }
}