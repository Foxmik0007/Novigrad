package com.example.novigrad30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.novigrad30.Class.Client.Client;
import com.example.novigrad30.Class.Service.ServicesHelperClass;
import com.example.novigrad30.Class.Staff.EmployeHelperClass;
import com.example.novigrad30.administrator.PortailAdministrateur;
import com.example.novigrad30.client.PortailClient;
import com.example.novigrad30.loading.*;
import com.example.novigrad30.staff.PortailSuccursale;

import java.util.ArrayList;
import java.util.List;

//import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private EditText password ;
    private Button login;
    private TextView sNumber;
    private static Client onlineCustomer;
    private static EmployeHelperClass onlineStaff;

    EditText userName;
    List<EmployeHelperClass>Crew;
    List<Client>Customer;
    List<ServicesHelperClass> u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etNom);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogin);
        sNumber = (TextView)findViewById(R.id.sNumber) ;

        Crew = new ArrayList<>();
        Crew = loading.crew;

        Customer = new ArrayList<>();
        Customer = loading.clients;

        u = new ArrayList<>();
        u = loading.products;
        sNumber.setText(Integer.toString(Crew.size()));

        onlineStaff = null;
        onlineCustomer = null;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // comparer les informations pour savoir si il peut visiter le portail administrateur
                String id  = userName.getText().toString();
                String pass = password.getText().toString();

                // Intent intentA = new Intent(getApplicationContext(),PortailAdministrateur.class);
                // startActivity(intentA);

                if ( id.equals("Admin") && pass.equals("ABC"))
                {
                    Intent intent = new Intent(getApplicationContext(), PortailAdministrateur.class);
                    startActivity(intent);
                }

              for (short i = 0; i < Crew.size() ; i++){
                  if ( id.equals(Crew.get(i).getID()) && pass.equals(Crew.get(i).getPassword()) )
                  {
                      onlineStaff = Crew.get(i);
                      Intent intent = new Intent(getApplicationContext(), PortailSuccursale.class);
                      startActivity(intent);
                  }
              }

                for (short i = 0; i < Customer.size() ; i++){
                    if ( id.equals(Customer.get(i).getNom()) && pass.equals(Customer.get(i).getPassword()) )
                    {
                        onlineCustomer = Customer.get(i);
                        Intent intent = new Intent(getApplicationContext(), PortailClient.class);
                        startActivity(intent);
                    }
                }


            }

        });
      //  sNumber.setText(Integer.toString(loading.clients.size()));
    }


    public static EmployeHelperClass getCurrentStaff (){
        return onlineStaff;
    }

    public static Client getCurrentClient(){ return onlineCustomer; }

    public boolean Verification (String id, String password){
        for (short i = 0; i < Crew.size();i++){
            if ( id.equals(Crew.get(i).getName()))
                return true;
        }
        return false;
    }


}