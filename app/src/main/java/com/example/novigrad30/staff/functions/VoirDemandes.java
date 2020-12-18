package com.example.novigrad30.staff.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.widget.ListView;

import com.example.novigrad30.Class.Demande.Demande;
import com.example.novigrad30.Class.Demande.demandeList;
import com.example.novigrad30.staff.list.DemandeList;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.staff.PortailSuccursale;
import com.example.novigrad30.staff.loading.loadingDemande;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoirDemandes extends AppCompatActivity {

    private static EmployeHelperClass currentUser = loadingDemande.getCurrentUser();
    //DatabaseReference databaseDemande;

    ListView listViewDemande;
    List<Demande> listDemandes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_serive);
        //getReference("SUCCURSALES").child("Succursale ottawa").child("Demandes").getRef()

        listDemandes =new ArrayList<>();

        listDemandes = loadingDemande.getListeDesDemandes();

        listViewDemande = (ListView)findViewById(R.id.ListViewService);

        demandeList adapter = new demandeList (VoirDemandes.this,listDemandes);
        listViewDemande.setAdapter(adapter);

    }


    public static EmployeHelperClass getCurrentUser() {
        return currentUser;
    }
}