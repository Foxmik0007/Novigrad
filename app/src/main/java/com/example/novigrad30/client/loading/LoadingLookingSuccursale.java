package com.example.novigrad30.client.loading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.client.functions.lookingType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoadingLookingSuccursale extends AppCompatActivity {


    private DatabaseReference DBservice;
    private DatabaseReference DBstaff;

    public static List<ServicesHelperClass> listeDeService = new ArrayList<>();
    public static List<EmployeHelperClass> listeDesEmployee = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_looking_succursale);

        DBservice = FirebaseDatabase.getInstance().getReference("SERVICES");
        DBstaff = FirebaseDatabase.getInstance().getReference("STAFF");


        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), lookingType.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DBstaff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    EmployeHelperClass staffMember = serviceSnap.getValue(EmployeHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < listeDesEmployee.size(); i++){
                        if (staffMember.getName() == listeDesEmployee.get(i).getName())
                            verify = true;
                    }
                    if (!verify)
                        listeDesEmployee.add(staffMember );
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DBservice.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < listeDeService.size(); i++){
                        if (service.getNom() == listeDeService.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        listeDeService.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }

    public static List<ServicesHelperClass> getListeDeService() {
        return listeDeService;
    }

    public static List<EmployeHelperClass> getListeDesEmployee() {
        return listeDesEmployee;
    }

}