package com.example.novigrad30.staff.dialogbox;

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

public class HeureDialog extends AppCompatDialogFragment {

    private boolean as;
    private EditText hdeb, hfin;
    private dialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_heure_dialog, null);

        builder.setView(view)
                .setTitle("UPDATE")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        as = false;
                    }
                })
                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newhd = hdeb.getText().toString();
                        String newhf = hfin.getText().toString();
                        as = true;
                        listener.applyText(newhd,newhf, as);

                    }
                });

        hdeb = (EditText) view.findViewById(R.id.heuredebut );
        hfin = (EditText) view.findViewById(R.id.heurefin);

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
        void applyText(String newHd, String newHf, Boolean as);
    }
}
