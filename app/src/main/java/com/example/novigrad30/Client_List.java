package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Client_List extends ArrayAdapter<Client> {

    private Activity context;
    private List<Client> ClientList;

    public Client_List(Activity context , List<Client> Client)
    {
        super(context,R.layout.activity_client__list, Client);
        this.context = context;
        ClientList = Client;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_client__list,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.nameClientList);

        Client client =  ClientList.get(position);

        textViewName.setText(client.getPrenom() + " " + client.getNom());
        return listViewItem;
    }
}