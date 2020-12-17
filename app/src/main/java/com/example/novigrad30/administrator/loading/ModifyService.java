package com.example.novigrad30.administrator.loading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.administrator.functions.changerService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyService extends AppCompatActivity {

    static DatabaseReference databaseServcice;
    private static List<ServicesHelperClass> ListeDeService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_modify_service );

        ListeDeService = new ArrayList<>();
        databaseServcice = FirebaseDatabase.getInstance().getReference("SERVICES");

        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ModifyService.this, changerService.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }




    //Chargement de la base de donnée
    @Override
    protected void onStart() {
        super.onStart();

        databaseServcice.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < ListeDeService.size(); i++){
                        if (service.getNom() == ListeDeService.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        ListeDeService.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }

    public static List<ServicesHelperClass> getListeDeService() {
        return ListeDeService;
    }
}