package com.example.novigrad30.Class.Staff;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.material.textfield.TextInputLayout;
import com.example.novigrad30.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeRg extends AppCompatActivity {
    //Declaration des variables à utilisé
    EditText regEmail , regName, ID, regPassword, regSuccName, regSuccAdress;
    Button inscription ;

    DatabaseReference StaffReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_rg);

        //CATCHING
        regEmail = (EditText) findViewById(R.id.reg_mail);
        regName = (EditText) findViewById(R.id.reg_nom);
        ID = (EditText) findViewById(R.id.reg_prenom);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regSuccName = (EditText) findViewById(R.id.SuccursaleName);
        regSuccAdress = (EditText) findViewById(R.id.SuccursaleAdress);


        inscription = (Button) findViewById(R.id.btnINSCRIPTION);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
               StaffReference = FirebaseDatabase.getInstance().getReference("STAFF");
                //recueillir toutes les donnees
                String name = regName.getText().toString().trim();
                String iD = ID.getText().toString().trim();
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                String SuccursaleName = regSuccName.getText().toString().trim();
                String SuccursaleAdress = regSuccAdress.getText().toString().trim();

                //RECUPERATION D'UNE CLÉE
                String id = StaffReference.push().getKey();

                //CREATION D'UNE ENTITÉ STAFF
                EmployeHelperClass employeHelperClass =new EmployeHelperClass(iD,name,email,password,SuccursaleName,SuccursaleAdress);
                StaffReference.child(name).setValue(employeHelperClass);
                Toast.makeText(EmployeRg.this, "Utilisateur ajoutée", Toast.LENGTH_SHORT).show();
            }
        });


    }
}