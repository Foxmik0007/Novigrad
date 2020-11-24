package com.example.novigrad30;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class SupprimerServiceSuccursale extends AppCompatActivity implements ServiceDialogue.dialogListener{

    private static EmployeHelperClass currentUser = PortailSuccursale.getCurrentUser();
    Boolean confirm;
    List<ServicesHelperClass> u;
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
        u = new ArrayList<>();

        u = PortailSuccursale.SuccursalServiceList;
        //u.add(a);

        ServicesList adapter = new ServicesList( SupprimerServiceSuccursale.this, u);
        listViewService.setAdapter( adapter );

        //Clickable
        listViewService.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceSelect = u.get(position);
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

        databaseServiceSuppression.child(name).setValue(null); // donner une valeur nulle a a l'element en question

        Toast.makeText(this, "Service deleted ", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void applyText(Boolean as) {

    }
}