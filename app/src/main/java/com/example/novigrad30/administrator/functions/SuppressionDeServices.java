package com.example.novigrad30.administrator.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.novigrad30.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuppressionDeServices extends AppCompatActivity {

    private Button suppression  ;
    private EditText nom_de_service ;
    DatabaseReference databaseServiceSuppression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppression_de_services);

        suppression = (Button)findViewById(R.id.btnSupprimerService);
        nom_de_service = (EditText)findViewById(R.id.etServiceMort);
        databaseServiceSuppression = FirebaseDatabase.getInstance().getReference("SERVICES");

        suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseServiceSuppression.child(nom_de_service.getText().toString()).setValue(null);
            }
        });


    }
}