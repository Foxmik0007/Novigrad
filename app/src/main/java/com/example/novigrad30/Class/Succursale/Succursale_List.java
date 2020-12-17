package com.example.novigrad30.Class.Succursale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.novigrad30.R;

import java.util.List;

public class Succursale_List extends ArrayAdapter<String> {

    private Activity context;
    private List<String> succursaleList;

    public Succursale_List(Activity context , List<String> succursale)
    {
        super(context, R.layout.activity_succursale__list, succursale);
        this.context = context;
        succursaleList = succursale;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_succursale__list,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.SuccursaleName);

        String succursale =  succursaleList.get(position);

        textViewName.setText(succursale);
        return listViewItem;
    }
}