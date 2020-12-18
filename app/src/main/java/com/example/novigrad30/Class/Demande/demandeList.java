package com.example.novigrad30.Class.Demande;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.novigrad30.R;

import java.util.List;

public class demandeList extends ArrayAdapter<Demande> {

    private Activity activity;
    private List<Demande> demandeList;

    public demandeList (Activity activity, List<Demande> demandes){
        super(activity,R.layout.activity_demande_list, demandes);
        this.activity = activity;
        this.demandeList =demandes;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_demande_list,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.nameAsked);
        TextView textViewDoc = (TextView) listViewItem.findViewById(R.id.serviceAsked);
        ImageView avatar = (ImageView) listViewItem.findViewById(R.id.avatar);

        Demande demande = demandeList.get(position);

        textViewName.setText(demande.getNom_client());
        textViewDoc.setText(demande.getService_demande());

        return listViewItem;

    }
}
