package com.example.novigrad30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class rating extends AppCompatActivity {

    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rating );


        rating=(RatingBar)findViewById( R.id.rating_bar );

                String r = String.valueOf( rating.getRating() );
                Toast.makeText( getApplicationContext(),r + " Star(s)" ,Toast.LENGTH_SHORT).show();

    }
}