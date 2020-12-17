package com.example.novigrad30.administrator.loading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.administrator.functions.GererSuccursale;
import com.example.novigrad30.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loadingGestionSuccursale extends AppCompatActivity {

    DatabaseReference SuccursaleDB;
    private static List<EmployeHelperClass> SuccurasaleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_gestion_succursale);

        SuccursaleDB = FirebaseDatabase.getInstance().getReference("STAFF");
        Handler handler = new Handler();

        //CONFIGURATION DELAIS D'ATTENTE
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loadingGestionSuccursale.this, GererSuccursale.class);
                startActivity(intent);
                finish();
            }

        }, 1250);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SuccursaleDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot serviceSnap : dataSnapshot.getChildren()){
                    EmployeHelperClass staffMember = serviceSnap.getValue(EmployeHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i < SuccurasaleList.size(); i++){
                        if (staffMember.getName() == SuccurasaleList.get(i).getName())
                            verify = true;
                    }
                    if (!verify)
                        SuccurasaleList.add(staffMember);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public static List<EmployeHelperClass> getSuccursaleList() {
        return SuccurasaleList;
    }

    public static void setSuccursaleList(List<EmployeHelperClass> succurasaleList) {
        SuccurasaleList = succurasaleList;
    }
}
