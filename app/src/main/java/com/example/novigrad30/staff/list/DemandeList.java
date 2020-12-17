package com.example.novigrad30.staff.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.novigrad30.Class.Demande.Demande;
import com.example.novigrad30.R;
import com.example.novigrad30.Class.Service.ServicesHelperClass;

import java.util.List;

public class DemandeList  extends ArrayAdapter<ServicesHelperClass> {
    private Activity context;
    private List<Demande> listDeDemandes;

    public DemandeList(Activity context , List<Demande> listDeDemandes)
    {
        super(context, R.layout.list_layout_demandes_succursales);
        this.context = context;
        this.listDeDemandes = listDeDemandes;

    }

    public DemandeList(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.list_layout_demandes_succursales,null,true);

        TextView textViewName = (TextView) ListViewItem.findViewById(R.id.textViewNomClient);
        TextView textViewEtat = (TextView) ListViewItem.findViewById(R.id.textView15);
        TextView textViewDocuments_Requis = (TextView)ListViewItem.findViewById(R.id.textView16);
        TextView textViewformulaires_fournis= (TextView)ListViewItem.findViewById(R.id.textView17);
        TextView textViewformulaires_requis = (TextView)ListViewItem.findViewById(R.id.textView18);
        TextView textViewService_demande = (TextView)ListViewItem.findViewById(R.id.textView19);
        TextView textViewcourriel_du_client = (TextView)ListViewItem.findViewById(R.id.textView20);
        TextView textViewjour_de_depot= (TextView)ListViewItem.findViewById(R.id.textView21);

        Demande demande = listDeDemandes.get(position);
        textViewName.setText("Nom du client : " + demande.getNom_client());
        textViewEtat.setText("Etat de la demande " + demande.getEtat()) ;
        textViewDocuments_Requis.setText("Documents Requis : "+ demande.getDocuments_Requis()) ;
        textViewformulaires_fournis.setText("Formulaires fournis : " + demande.getFormulaires_fournis());
        textViewformulaires_requis.setText("Formulaires Requis " + demande.getFormulaires_requis()) ;
        textViewService_demande.setText("Service demande : " + demande.getService_demande()) ;
        textViewcourriel_du_client.setText("Courriel du client : " +  demande.getCourriel_du_client());
        textViewjour_de_depot.setText("Jour de depot " + demande.getJour_de_depot());

        return ListViewItem;
    }
}
