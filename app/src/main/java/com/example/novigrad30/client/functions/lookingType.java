package com.example.novigrad30.client.functions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novigrad30.Class.Client.Client;
import com.example.novigrad30.client.dialogbox.DialogAdressLooking;
import com.example.novigrad30.client.dialogbox.DialogServiceLooking;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.client.PortailClient;
import com.example.novigrad30.client.loading.LoadingLookingSuccursale;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class lookingType extends AppCompatActivity implements DialogAdressLooking.dialogListener, DialogServiceLooking.dialogListener {

    private Client currentUser;
    private Button adress;
    private Button service;
    private Button heures;
    private TextView sr;

    short count = 0;
    private String adressLook;

    private List<EmployeHelperClass>listeDesEmployee;
    private List<ServicesHelperClass>listeDesServices;
    private static List<EmployeHelperClass> employeeSelected;

    DatabaseReference DBservices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_type);

        listeDesEmployee = new ArrayList<>();
        listeDesServices = new ArrayList<>();
        employeeSelected = new ArrayList<>();

        listeDesEmployee = LoadingLookingSuccursale.getListeDesEmployee();
        listeDesServices = LoadingLookingSuccursale.getListeDeService();
        currentUser = PortailClient.getCurrentClient();

        adress = (Button) findViewById(R.id.adressSearch);
        service = (Button) findViewById(R.id.serviceSearch);
        heures = (Button) findViewById(R.id.shiftSearch);
        sr = (TextView) findViewById(R.id.sr);

        sr.setText(Integer.toString(listeDesEmployee.size()));

        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdressDialog();
            }
        });


        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openServiceDialog();
            }
        });

        heures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void openAdressDialog (){
        DialogAdressLooking adressLooking = new DialogAdressLooking();
        adressLooking.show(getSupportFragmentManager(), "Searching");
    }

    public void applyTextAdress(String adress){
        short count = 0;

        //verification de la liste
        for (short i = 0; i < listeDesEmployee.size(); i ++){
            if (listeDesEmployee.get(i).getAdressSuccursale().equals(adress) && !listeDesEmployee.get(i).getAdressSuccursale().isEmpty()){
                count++;
                boolean check = true;
                for (short k = 0; k < employeeSelected.size(); k++)
                    if (listeDesEmployee.get(i).getAdressSuccursale().equals(employeeSelected.get(k).getAdressSuccursale()))
                        check = false;
                if (check)
                    employeeSelected.add(listeDesEmployee.get(i));
            }
        }

        if (count != 0)
            Toast.makeText(getApplicationContext(), count + " Match found" , Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "No Match Found" , Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), listAsked.class);
        startActivity(intent);
    }

    public void openServiceDialog(){
        DialogServiceLooking serviceLooking = new DialogServiceLooking();
        serviceLooking.show(getSupportFragmentManager(), "Searching");
    }

    public void applyTextService(final String service){
        for (short i = 0; i < listeDesEmployee.size(); i++) {
            DBservices = FirebaseDatabase.getInstance().getReference("SUCCURSALES/" + listeDesEmployee.get(i).getNomSuccursale() + "/Services Offerts");
            final List<ServicesHelperClass>prox = new ArrayList<>();

           DBservices.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot serviceSnap : snapshot.getChildren()) {
                        ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);

                        //***********************
                        Boolean verify = false;

                        for (short i = 0; i < prox.size(); i++){
                            if (service.getNom() == prox.get(i).getNom())
                                verify = true;
                        }
                        if (!verify)
                            prox.add( service );

                        //*************************

                    }
                    for (short i = 0; i< prox.size(); i++){
                        if (prox.get(i).getNom().equals(service))
                            count++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });

            if (count != 0)
                Toast.makeText(getApplicationContext(), count + " Match found" , Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "No Match Found" , Toast.LENGTH_SHORT).show();
        }
    }

    public static List<EmployeHelperClass> getEmployeeSelected() {
        return employeeSelected;
    }

    public static void setEmployeeSelected(List<EmployeHelperClass> employeeSelected) {
        lookingType.employeeSelected = employeeSelected;
    }
}