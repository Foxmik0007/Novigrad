package com.example.novigrad30;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUPRedirection extends AppCompatActivity {
    Button client, employe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p_redirection);
        client = (Button)findViewById(R.id.btnClient);
        employe = (Button)findViewById(R.id.btnEmployee);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ClientRg.class);
                startActivity(intent);
            }
        });

        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(),EmployeRg.class);
                 startActivity(intent);
            }
        });
    }
}