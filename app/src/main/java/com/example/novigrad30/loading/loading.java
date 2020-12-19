package com.example.novigrad30.loading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.novigrad30.Class.Client.Client;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.MainActivity;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.SignUPRedirection;
import com.example.novigrad30.Test_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loading extends AppCompatActivity {

    static DatabaseReference Staff;
    static DatabaseReference Clients;
    static DatabaseReference Services;

    public static List <EmployeHelperClass> crew;
    public static List<Client>clients;
    public static List <ServicesHelperClass> products;

    private Button connect, register, testing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        connect = (Button) findViewById(R.id.connect);
        register = (Button) findViewById(R.id.register);
        testing = (Button) findViewById(R.id.testing);

        crew = new ArrayList<>();
        clients = new ArrayList<>();
        products = new ArrayList<>();

        Staff = FirebaseDatabase.getInstance().getReference("STAFF");
        Clients = FirebaseDatabase.getInstance().getReference("CLIENTS");
        Services = FirebaseDatabase.getInstance().getReference("SERVICES");

        //Handler handler = new Handler();

       EmployeHelperClass admin = new EmployeHelperClass("Staff1", "Staff", "0@mail.ca","ABC", "QG", "Global");
        crew.add(admin);

        Client client = new Client("0","Client0", "Client0", "ABC", "0@mail.ca");
        clients.add(client);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loading.this, MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loading.this, SignUPRedirection.class);
                startActivity(intent);
            }
        });

        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Test_Activity.class);
                startActivity(intent);
            }
        });

        //Q.setText(crew.size());

/*
        //CONFIGURATION DU DELAIS DE  TRAITEMENT
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loading.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);*/
    }


    //RECUPERATION DONNEES POUR LE LOGIN
    @Override
    protected void onStart() {
        super.onStart();

        Staff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    EmployeHelperClass staffMember = serviceSnap.getValue(EmployeHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < crew.size(); i++){
                        if (staffMember.getName() == crew.get(i).getName())
                            verify = true;
                    }
                    if (!verify)
                        crew.add(staffMember );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Clients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    Client client = serviceSnap.getValue(Client.class);
                    Boolean verify = false;

                    for (short i = 0; i < clients.size(); i++){
                        if (client.getNom() == clients.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        clients.add(client );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Services.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < products.size(); i++){
                        if (service.getNom() == products.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        products.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

    }


}