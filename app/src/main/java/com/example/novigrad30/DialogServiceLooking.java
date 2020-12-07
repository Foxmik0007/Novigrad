package com.example.novigrad30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class DialogServiceLooking extends AppCompatDialogFragment {

    private List<String> listeDesAdresse = new ArrayList<>();
    private List<EmployeHelperClass> listeDesEmployee = new ArrayList<>();
    private EditText service;

    private dialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        listeDesEmployee = LoadingLookingSuccursale.getListeDesEmployee();
        charger(listeDesEmployee, listeDesAdresse);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_service_looking, null);


        builder .setView(view)
                .setTitle("Recherche par service demandé")
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String values = service.getText().toString();
                        listener.applyTextService(values);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        service = (EditText) view.findViewById(R.id.serviceSearching);

        return builder.create();

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (dialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "dialogListener n'a pas été implementé");
        }
    }

    public interface dialogListener {
        void applyTextService(String adress);
    }


    public void charger (List<EmployeHelperClass> ListeEmployee, List<String> Liste){
        for (short i = 0; i <ListeEmployee.size(); i++) {
            Liste.add(ListeEmployee.get(i).getName());
        }
    }

}