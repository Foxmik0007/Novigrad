package com.example.novigrad30.client.functions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.Class.Succursale.Succursale_List;
import com.example.novigrad30.administrator.loading.loadingGestionSuccursale;
import com.example.novigrad30.client.dialogbox.dialogChoice;
import com.example.novigrad30.client.dialogbox.dialogRating;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listAsked extends AppCompatActivity implements dialogRating.dialogListener, dialogChoice.dialogListener {

        static EmployeHelperClass succursaleSelected;
        String suppress;
        ListView list;
        private static List<EmployeHelperClass> succursaleList;
        private static List<String> nomSuccursale;
        private static List<ServicesHelperClass> currentService;


        DatabaseReference succursaleDB;
        DatabaseReference succursaleServiceDB;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_see_serive );

        succursaleSelected = new EmployeHelperClass();
        nomSuccursale = new ArrayList<>();
        currentService = new ArrayList<>();
        succursaleList = new ArrayList<>();
        suppress = new String();

        list = (ListView)findViewById(R.id.ListViewService);
        succursaleDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES");
        succursaleServiceDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + succursaleSelected.getNomSuccursale() + "/Services Offerts");

        succursaleList = lookingType.getEmployeeSelected();

    recuperation(succursaleList, nomSuccursale);

        Succursale_List adapter = new Succursale_List(listAsked.this, nomSuccursale);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
@Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        //Recherche de l'employé correspondant
        for (short i = 0; i < succursaleList.size(); i++){
                if (succursaleList.get(i).getNomSuccursale().equals(nomSuccursale.get(position)))
                 succursaleSelected = succursaleList.get(i);


                 }

    succursaleServiceDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + succursaleSelected.getNomSuccursale() + "/Services Offerts");
        Toast.makeText(listAsked.this, succursaleSelected.getName() + " selected", Toast.LENGTH_SHORT).show();

        openChoiceDialog();
        return true;
        }
        });

        }

public void openRatingDialog(){
        dialogRating dialogGerer = new dialogRating();
        dialogGerer.show(getSupportFragmentManager(), "Rating");
        }

public void applyTextRating(String as) {


        succursaleDB.child(succursaleSelected.getNomSuccursale() + "/Rating").setValue(as);
        loadingGestionSuccursale.setSuccursaleList(null);

        //Rechargement de la page
        Intent intent = new Intent(getApplicationContext(), lookingType.class);
        startActivity(intent);

        }
public void openChoiceDialog(){
    dialogChoice dialogChoice = new dialogChoice();
    dialogChoice.show(getSupportFragmentManager(), "Choosing");

}

public void applyTextChoice(Boolean as){
    if (as){
      Intent intent2 = new Intent(listAsked.this, SelectionServicesClients.class);
      startActivity(intent2);
    } else
        openRatingDialog();

}


public static List<EmployeHelperClass> getSuccursaleList() {
        return succursaleList;
        }

public void recuperation( List<EmployeHelperClass> Employee, List<String> nomSuccursale){
        for (short i = 0; i < Employee.size(); i ++){
        nomSuccursale.add(Employee.get(i).getNomSuccursale());
        }
        }


    public static EmployeHelperClass getSuccursaleSelected() {
        return succursaleSelected;
    }

    public String getSuppress() {
        return suppress;
    }

    public static List<String> getNomSuccursale() {
        return nomSuccursale;
    }

    public static List<ServicesHelperClass> getCurrentService() {
        return currentService;
    }

    @Override
    protected void onStart() {
        super.onStart();

        succursaleServiceDB.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < currentService.size(); i++){
                        if (service.getNom() == currentService.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        currentService.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
}