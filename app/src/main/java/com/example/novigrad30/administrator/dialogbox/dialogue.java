package com.example.novigrad30.administrator.dialogbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.novigrad30.R;

public class dialogue extends AppCompatDialogFragment {

    private EditText documents, formulaires;
    private dialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue, null);

        builder.setView(view)
                .setTitle("UPDATE")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newDoc = documents.getText().toString();
                        String newFom = formulaires.getText().toString();
                        listener.applyText(newDoc,newFom);
                    }
                });

        documents = (EditText) view.findViewById(R.id.Documents);
        formulaires = (EditText) view.findViewById(R.id.Form);

        return builder.create();
    }
//Activation de la boite d'ecoute
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (dialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "dialogListener n'a pas été implementé");
        }
    }

    public interface dialogListener {
        void applyText(String newDoc, String newForm);
    }
}
