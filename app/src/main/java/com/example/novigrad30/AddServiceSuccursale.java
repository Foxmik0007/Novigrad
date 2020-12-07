package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddServiceSuccursale extends AppCompatActivity implements ServiceDialogue.dialogListener{

    private static EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    Boolean confirm;
    List<ServicesHelperClass> u;
    ListView listViewService;
    ServicesHelperClass ServiceSelect;
    DatabaseReference SuccursaleDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SuccursaleDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/"+ currentUser.getNomSuccursale() + "/Services Offerts");

        //Affichage en ListView
        // Ici tu vas lier ça au layout de la liste que tu vas utiliser
        setContentView( R.layout.activity_see_serive );
        listViewService=(ListView)findViewById(R.id.ListViewService);

        //Copie des donnees recu dans l'Activité precedente
        u = new ArrayList<>();
        u = PortailSuccursale.ServiceList;

        ServicesList adapter = new ServicesList( AddServiceSuccursale.this, u);
        listViewService.setAdapter( adapter );

        //Clickable
        listViewService.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceSelect = u.get(position);
                openDialog();
                return true;
            }
        } );

    }

    //Ouverture de la boite de dialogue
    public void openDialog(){
        ServiceDialogue updateDialogue = new ServiceDialogue();
        updateDialogue.show(getSupportFragmentManager(), "Confirmation");
    }

    //Après validation
    @Override
    public void applyText(Boolean as) {
        confirm = as;
        if (as){
            SuccursaleDB.child(ServiceSelect.nom).setValue(ServiceSelect);
        }
    }

    public static EmployeHelperClass getCurrentUser() {
        return currentUser;
    }
}