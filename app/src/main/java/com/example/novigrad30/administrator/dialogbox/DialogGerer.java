package com.example.novigrad30.administrator.dialogbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogGerer extends AppCompatDialogFragment {

    private dialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //LayoutInflater inflater = getActivity().getLayoutInflater();
        //View view = inflater.inflate(R.layout.activity_service_dialogue, null);

        builder/*.setView(view)*/
                .setTitle("Suppression")
                .setMessage("Voulez vous supprimer ?" )
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.applyText(true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

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
        void applyText(Boolean as);
    }
}
