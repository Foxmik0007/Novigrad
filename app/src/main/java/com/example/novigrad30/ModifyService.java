package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyService extends AppCompatActivity {
    private EditText servicename;
    private  Button list;
    private EditText doc;
    private  EditText form;

    static DatabaseReference databaseServcice;
    static List<ServicesHelperClass> ListeDeService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_modify_service );
        list=(Button)findViewById( R.id.Listbtn );

        ListeDeService = new ArrayList<>();
        databaseServcice = FirebaseDatabase.getInstance().getReference("SERVICES");

        list.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyService.this,changerService.class );
                startActivity(intent);
            }
        } );
    }
    private void addService()
    {
        String name = servicename.getText().toString().trim();
        String docad = doc.getText().toString().trim();
        String formad = form.getText().toString().trim();
        if (!TextUtils.isEmpty( name ))
        {
            String id= databaseServcice.push().getKey();
            Service service=new Service(id,name,docad,formad);
            databaseServcice.child( id).setValue( service );
            Toast.makeText( this,"Service added",Toast.LENGTH_SHORT ).show();
        }
        else    {
            Toast.makeText( ModifyService.this, "Champ Invalide", Toast.LENGTH_LONG ).show();
        }
    }

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

    public static void update (final List<ServicesHelperClass> proto){
        databaseServcice.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnap : snapshot.getChildren()){
                    ServicesHelperClass service = serviceSnap.getValue(ServicesHelperClass.class);
                    Boolean verify = false;

                    for (short i = 0; i <= proto.size(); i++){
                        if (service.getNom() == proto.get(i).getNom())
                            verify = true;
                    }
                    if (!verify)
                        proto.add( service );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
}