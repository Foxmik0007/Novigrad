package com.example.novigrad30.administrator.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.novigrad30.Class.Client.Client;
import com.example.novigrad30.client.List.Client_List;
import com.example.novigrad30.administrator.dialogbox.DialogGerer;
import com.example.novigrad30.R;
import com.example.novigrad30.administrator.loading.loadingGestionClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GererClient extends AppCompatActivity implements DialogGerer.dialogListener {

    Client clientSelected;
    ListView list;
    private List<Client>customerList = new ArrayList<>();
    DatabaseReference client;
    boolean confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_see_serive );
        list = (ListView)findViewById(R.id.ListViewService  );
        client = FirebaseDatabase.getInstance().getReference("CLIENTS");

        customerList = loadingGestionClient.getClients();

        Client_List adapter = new Client_List(GererClient.this, customerList);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                clientSelected = customerList.get(position);
                openDialog();
                return true;
            }
        });

    }

    public void openDialog(){
        DialogGerer dialogGerer = new DialogGerer();
        dialogGerer.show(getSupportFragmentManager(), "Suppression");
    }

    public void applyText(Boolean as) {
        confirm = as;
        if (as){
            client.child(clientSelected.getPrenom()).setValue(null);
            customerList.remove(clientSelected);
            loadingGestionClient.setClients(customerList);
        }
    }

    public List<Client> getCustomerList() {
        return customerList;
    }
}