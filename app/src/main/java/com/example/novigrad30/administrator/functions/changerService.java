
package com.example.novigrad30.administrator.functions;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.ServicesList;
import com.example.novigrad30.administrator.loading.ModifyService;
import com.example.novigrad30.administrator.dialogbox.dialogue;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class changerService extends AppCompatActivity implements dialogue.dialogListener {
    DatabaseReference databaseService;
    ListView listViewService;
    // Liste pour l'affichage

    List<ServicesHelperClass> u;
    String newName, newDocs, newForms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //Recuperation du layout pour afficher la liste
        setContentView( R.layout.activity_see_serive );
       listViewService=(ListView)findViewById(R.id.ListViewService  );

        u = new ArrayList<>();

        //Recuperation des données à afficher
        u = ModifyService.getListeDeService();

        databaseService= FirebaseDatabase.getInstance().getReference("SERVICES");

        ServicesList adapter = new ServicesList( changerService.this, u);
        listViewService.setAdapter( adapter );

        listViewService.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog();
                newName = u.get(position).getNom();
              return true;
            }
        } );

    }

    public void openDialog(){
            dialogue updateDialogue = new dialogue();
            updateDialogue.show(getSupportFragmentManager(), "Updating");
    }


    //Mise à jour des donnees
    private  boolean update(String nom, String document, String formulaire)
    {

        //Verification des donnees reçues

            //Reception de nouvelles donnees
            String id = databaseService.push().getKey();
            ServicesHelperClass service = new ServicesHelperClass(id,nom,document,formulaire);
            databaseService.child(nom).setValue(service);
            Toast.makeText( changerService.this,"Update succesful",Toast.LENGTH_LONG ).show();
            return true ;
        //}

    }

    // Recuperation des donnees de la boite de dialogue et Update
    @Override
    public void applyText(String newDoc, String newForm) {
        newDocs = newDoc;
        newForms = newForm;
        update(newName, newDocs, newForms);
    }
}
