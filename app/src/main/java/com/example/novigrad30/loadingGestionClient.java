package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loadingGestionClient extends AppCompatActivity {

    DatabaseReference ClientList;
    private static List<Client> Clients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_gestion_client);

        ClientList = FirebaseDatabase.getInstance().getReference("CLIENTS");

        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loadingGestionClient.this, GererClient.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ClientList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    Client client = serviceSnap.getValue(Client.class);
                    Boolean verify = false;

                    for (short i = 0; i < Clients.size(); i++){
                        if (client.getNom() == Clients.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        Clients.add(client);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static List<Client> getClients() {
        return Clients;
    }

    public static void setClients(List<Client> clients) {
        Clients = clients;
    }
}