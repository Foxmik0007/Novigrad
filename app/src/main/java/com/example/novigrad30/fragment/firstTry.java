package com.example.novigrad30.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.novigrad30.MainActivity;
import com.example.novigrad30.R;
import com.example.novigrad30.client.PortailClient;


public class firstTry extends Fragment {

   private Button Home;
   private Button Account;
   private Button Settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first_try, container, true);

        Home = (Button) rootView.findViewById(R.id.btnHome);
        Account = (Button) rootView.findViewById(R.id.btnAccount);
        Settings = (Button) rootView.findViewById(R.id.btnSettings);

        Home.setOnClickListener(btnHomeListener);
        return rootView;
    }

    private View.OnClickListener btnHomeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent (getContext(), MainActivity.class);
            startActivity(intent);
        }
    };
}