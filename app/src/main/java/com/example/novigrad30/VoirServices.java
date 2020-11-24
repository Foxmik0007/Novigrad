package com.example.novigrad30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class VoirServices extends AppCompatActivity {

    private static EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    List<ServicesHelperClass> u;
    ListView listViewService;
    ServicesHelperClass ServiceSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Affichage en ListView
        // Ici tu vas lier ça au layout de la liste que tu vas utiliser
        setContentView( R.layout.activity_see_serive );
        listViewService=(ListView)findViewById(R.id.ListViewService  );

        //Copie des donnees recu dans l'Activité precedente
        u = new ArrayList<>();

        u = PortailSuccursale.SuccursalServiceList;
        //u.add(a);

        ServicesList adapter = new ServicesList( VoirServices.this, u);
        listViewService.setAdapter( adapter );

    }
}