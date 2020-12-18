package com.example.novigrad30.staff.functions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.client.PortailClient;
import com.example.novigrad30.client.dialogbox.ServiceDialogue;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.ServicesList;
import com.example.novigrad30.staff.PortailSuccursale;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


import android.widget.Toast;

public class SupprimerServiceSuccursale extends AppCompatActivity implements ServiceDialogue.dialogListener {

    private static EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    Boolean confirm;
    List<ServicesHelperClass> newServiceSuccursaleList;
    ListView listViewService;
    ServicesHelperClass ServiceSelect;
    DatabaseReference SuccursaleDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SuccursaleDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + currentUser.getNomSuccursale() + "/Services Offerts");

        //Affichage en ListView
        // Ici tu vas lier ça au layout de la liste que tu vas utiliser
        setContentView( R.layout.activity_see_serive );
        listViewService=(ListView)findViewById(R.id.ListViewService  );

        //Copie des donnees recu dans l'Activité precedente
        newServiceSuccursaleList = new ArrayList<>();

        newServiceSuccursaleList = PortailSuccursale.getSuccursalServiceList();
        //u.add(a);

        ServicesList adapter = new ServicesList( SupprimerServiceSuccursale.this, newServiceSuccursaleList);
        listViewService.setAdapter( adapter );

        //Clickable
        listViewService.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceSelect = newServiceSuccursaleList.get(position);
                showConfirmationDialog(ServiceSelect.getServiceId(),ServiceSelect.getNom(),ServiceSelect.getDocuments(),ServiceSelect.getFormulaireRequis());
                return true;
            }
        } );

    }

    //Ouverture de la boite de dialogue
    public void openDialog(){
        ServiceDialogue updateDialogue = new ServiceDialogue();
        updateDialogue.show(getSupportFragmentManager(), "Confirmation");
    }



    private void showConfirmationDialog(String Serviceid , final String ServiceName , String ServiceDoc, String ServiceFormulaire)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogue_supprimer_service_succursale,null);
        dialogBuilder.setView(dialogView);
        // final TextView textViewName = (TextView) dialogView.findViewById(R.id.textViewNomService);
       final Button confirmer = (Button)dialogView.findViewById(R.id.buttonConfirmer);
       final Button annuler = (Button)dialogView.findViewById(R.id.buttonAnnuler);
        dialogBuilder.setTitle(" Suppression de : " + ServiceName);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              deleteService(ServiceName);
                alertDialog.dismiss();
            }
        });




        //Après validation
 /*   @Override
    public void applyText(Boolean as) {
        confirm = as;
        if (as){
            SuccursaleDB.child(ServiceSelect.nom).setValue(ServiceSelect);
        }
    }*/

    /*public static EmployeHelperClass getCurrentUser() {
        return currentUser;
    }*/


        }

    private void deleteService(String name) {
        DatabaseReference databaseServiceSuppression = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + currentUser.getNomSuccursale() + "/Services Offerts");

        //Mise à jour de la base de donnée locale
        for (short i = 0; i < newServiceSuccursaleList.size(); i++){
            if(newServiceSuccursaleList.get(i).getNom() == name)
                newServiceSuccursaleList.remove(newServiceSuccursaleList.get(i));
        }
        PortailSuccursale.setSuccursalServiceList(newServiceSuccursaleList);

        //Mise à jour de la base de donnée sur le cloud
        databaseServiceSuppression.child(name).setValue(null);

        Intent intent = new Intent(getApplicationContext(), SupprimerServiceSuccursale.class);
        startActivity(intent);

        Toast.makeText(this, "Service deleted ", Toast.LENGTH_SHORT).show();
        finish();


    }


    @Override
    public void applyText(Boolean as) {

    }
}