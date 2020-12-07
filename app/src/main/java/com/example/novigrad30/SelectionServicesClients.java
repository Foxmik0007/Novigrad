package com.example.novigrad30;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class SelectionServicesClients extends AppCompatActivity implements ServiceDialogue.dialogListener {


    private static EmployeHelperClass currentUser = /* MainActivity.get_a_staff_for_test();*/ listAsked.getSuccursaleSelected();
    public static Service chosen_one;
    public static String choice_service;
    List<ServicesHelperClass> a;

    ServicesHelperClass ServiceSelect;


    Boolean confirm;
    ListView listViewService;
    DatabaseReference SuccursaleDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SuccursaleDB = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + currentUser.getNomSuccursale() +"/Services Offerts");

        //Affichage en ListView
        // Ici tu vas lier ça au layout de la liste que tu vas utiliser
        setContentView( R.layout.activity_see_serive );
        listViewService=(ListView)findViewById(R.id.ListViewService  );

        //Copie des donnees recu dans l'Activité precedente
        a = new ArrayList<>();


        //liste des service de la succursale actuellement utilisee
        a = listAsked.getCurrentService();
        //u.add(a);

        ServicesList adapter = new ServicesList( SelectionServicesClients.this, a);
        listViewService.setAdapter( adapter );

        //Clickable
        listViewService.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceSelect = a.get(position);
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

    private void showConfirmationDialog(final String Serviceid , final String ServiceName , final String ServiceDoc, final String ServiceFormulaire)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogue_soumettre_demande,null);
        dialogBuilder.setView(dialogView);
        // final TextView textViewName = (TextView) dialogView.findViewById(R.id.textViewNomService);
        final Button confirmer = (Button)dialogView.findViewById(R.id.buttonConfirmer2);
        final Button annuler = (Button)dialogView.findViewById(R.id.buttonAnnuler2);
        dialogBuilder.setTitle(" Choix de  : " + ServiceName);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chosen_one = new Service(Serviceid,ServiceName,ServiceDoc,ServiceFormulaire);
                alertDialog.dismiss();
                if(ServiceName.equals("Permis de Conduire")) // formulaire particulier
                {
                    Intent intent_pc = new Intent(getApplicationContext(),Pc_form.class);
                    startActivity(intent_pc);
                    choice_service = "pc";

                }
                else
                    {
                        Intent intent = new Intent(getApplicationContext(),Form.class);
                        startActivity(intent);
                        choice_service = "other";

                    }

            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }



    public static Service getChosen_one(){return  chosen_one;}

    public static String getChoice_form()
    {
        return choice_service;
    }
    @Override
    public void applyText(Boolean as) {

    }
}