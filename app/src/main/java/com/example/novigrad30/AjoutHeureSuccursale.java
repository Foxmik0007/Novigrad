package com.example.novigrad30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AjoutHeureSuccursale extends AppCompatActivity implements HeureDialog.dialogListener{

    private EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    private ListView listView;
    String debut,fin;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_heure_succursale);

        listView=(ListView)findViewById( R.id.listView);
        databaseReference= FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + currentUser.getNomSuccursale() );
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Lundi");
        arrayList.add("Mardi");
        arrayList.add("Mercredi");
        arrayList.add("Jeudi");
        arrayList.add("Vendredi");
        arrayList.add("Samedi");
        arrayList.add("Dimanche");

        ArrayAdapter arrayAdapter= new ArrayAdapter( this, android.R.layout.simple_list_item_1, arrayList );
        // ListAdapter adapter=new ListAdapter(MainActivity.this,  arrayList  );
        listView.setAdapter( arrayAdapter );
        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog();

                return true;
            }
        } );

    }

    public void openDialog(){
        HeureDialog updateDialogue = new HeureDialog();
        updateDialogue.show(getSupportFragmentManager(), "Selection Horaire");
    }

    public void applyText(String newhd, String newhf, Boolean as) {
        debut = newhd;
        fin = newhf;
        if (as){
            addHours(debut, fin);
        }
    }

    private void addHours(String serviceBegin, String serviceEnding)
    {
        if(!TextUtils.isEmpty(debut ) && !TextUtils.isEmpty(fin) )
        {
            String id = databaseReference.push().getKey();

            Heure heure = new Heure(serviceBegin,serviceEnding);
            databaseReference.child("Horaires").setValue(heure);

            Toast.makeText(this, "Horaire defini ", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "Entrez des valeurs valides ", Toast.LENGTH_SHORT).show();
        }

    }

    public EmployeHelperClass getCurrentUser() {
        return currentUser;
    }
}