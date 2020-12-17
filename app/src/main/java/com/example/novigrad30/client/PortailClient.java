package com.example.novigrad30.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.novigrad30.Class.Client.Client;
import com.example.novigrad30.MainActivity;
import com.example.novigrad30.R;
import com.example.novigrad30.client.loading.LoadingLookingSuccursale;

public class PortailClient extends AppCompatActivity {

    private static Client currentClient = MainActivity.getCurrentClient();
    private Button lookFor;
    private Button seeApplication;
    private Button rating;
    private TextView welcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail_client);

        lookFor = (Button) findViewById(R.id.lookForSuccursale);
        seeApplication = (Button) findViewById(R.id.seeApplication);
        welcoming = (TextView) findViewById(R.id.welcomeClient);
        rating = (Button) findViewById(R.id.rating);

        welcoming.setText("Bienvenue " + currentClient.getNom());

        lookFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoadingLookingSuccursale.class);
                startActivity(intent);
            }
        });

        seeApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rating.class);
                startActivity(intent);
            }
        });
    }

    public static Client getCurrentClient() {
        return currentClient;
    }
}