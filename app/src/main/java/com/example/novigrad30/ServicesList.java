package com.example.novigrad30;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.novigrad30.Class.Service.ServicesHelperClass;

import java.util.List;

public class ServicesList extends ArrayAdapter<ServicesHelperClass> {

    private Activity context;
    private List<ServicesHelperClass> listDeServices;

    public ServicesList(Activity context , List<ServicesHelperClass> listDeServices)
    {
        super(context,R.layout.list_layout,listDeServices);
        this.context = context;
        this.listDeServices = listDeServices;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.tvNom);
        TextView textViewDoc = (TextView) listViewItem.findViewById(R.id.tvDocumentsRequis);
        TextView textViewform = (TextView)listViewItem.findViewById(R.id.tvFormulaire);

        ServicesHelperClass  servicesHelperClass = listDeServices.get(position);

        textViewName.setText(servicesHelperClass.getNom());
        textViewDoc.setText(servicesHelperClass.getDocuments());
        textViewform.setText(servicesHelperClass.getFormulaireRequis());
        return listViewItem;


    }
}

