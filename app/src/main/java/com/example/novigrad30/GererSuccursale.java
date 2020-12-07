package com.example.novigrad30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GererSuccursale extends AppCompatActivity implements DialogGerer.dialogListener{

    EmployeHelperClass succursaleSelected = new EmployeHelperClass();
    String suppress = new String();
    ListView list;
    private List<EmployeHelperClass> succursaleList = new ArrayList<>();
    private List<String> nomSuccursale = new ArrayList<>();
    DatabaseReference succursaleDB;
    boolean confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_see_serive );
        list = (ListView)findViewById(R.id.ListViewService);
        succursaleDB = FirebaseDatabase.getInstance().getReference("STAFF");

        succursaleList = loadingGestionSuccursale.getSuccursaleList();

        recuperation(succursaleList, nomSuccursale);

        Succursale_List adapter = new Succursale_List(GererSuccursale.this, nomSuccursale);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //Recherche de l'employé correspondant
                for (short i = 0; i < succursaleList.size(); i++){
                    if (succursaleList.get(i).getNomSuccursale().equals(nomSuccursale.get(position)))
                        succursaleSelected = succursaleList.get(i);
                }
                Toast.makeText(GererSuccursale.this, succursaleSelected.getName() + " selected", Toast.LENGTH_SHORT).show();
                openDialog();


                return true;
            }
        });

    }

    public void openDialog(){
        DialogGerer dialogGerer = new DialogGerer();
        dialogGerer.show(getSupportFragmentManager(), "Suppression");
    }

    public void applyText(Boolean as) {
        confirm = as;
        if (as){
            succursaleDB.child(succursaleSelected.getName()).setValue(null);
            succursaleList.remove(succursaleSelected);
            loadingGestionSuccursale.setSuccursaleList(null);

            //Rechargement de la page
            Intent intent = new Intent(getApplicationContext(), loadingGestionSuccursale.class);
            startActivity(intent);
        }
    }




    public List<EmployeHelperClass> getSuccursaleList() {
        return succursaleList;
    }

    public void recuperation( List<EmployeHelperClass> Employee, List<String> nomSuccursale){
        for (short i = 0; i < Employee.size(); i ++){
                nomSuccursale.add(Employee.get(i).getNomSuccursale());
        }
    }

}