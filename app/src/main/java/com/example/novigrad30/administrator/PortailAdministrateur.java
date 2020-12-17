package com.example.novigrad30.administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.novigrad30.R;
import com.example.novigrad30.administrator.functions.AjoutServices;
import com.example.novigrad30.administrator.loading.ModifyService;
import com.example.novigrad30.administrator.functions.SuppressionDeServices;
import com.example.novigrad30.administrator.loading.loadingGestionClient;
import com.example.novigrad30.administrator.loading.loadingGestionSuccursale;

public class PortailAdministrateur extends AppCompatActivity {

    Button gererClient, gererSuccursales , AjouterServices,SupprimerServices,ModifierServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail_administrateur);

        gererClient = (Button)findViewById(R.id.btnGestionClient);
        gererSuccursales = (Button)findViewById(R.id.btnGestionSuccursales);
        AjouterServices = (Button)findViewById(R.id.btnCreationServices);
        SupprimerServices = (Button)findViewById(R.id.btnSuppressionServices);
        ModifierServices = (Button)findViewById(R.id.btnModificationServices);

        AjouterServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), AjoutServices.class);
                startActivity(intent2);

            }
        });

        ModifierServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modiIntent = new Intent(PortailAdministrateur.this, ModifyService.class);
                startActivity(modiIntent);
            }
        });

        SupprimerServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuppressionDeServices.class);
                startActivity(intent);
            }
        });

        gererClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), loadingGestionClient.class);
                startActivity(intent);
            }
        });

        gererSuccursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), loadingGestionSuccursale.class);
                startActivity(intent);
            }
        });

    }
}