package com.example.novigrad30.staff.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.ServicesList;
import com.example.novigrad30.staff.PortailSuccursale;

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

        u = PortailSuccursale.getSuccursalServiceList();
        //u.add(a);

        ServicesList adapter = new ServicesList( VoirServices.this, u);
        listViewService.setAdapter( adapter );

    }
}