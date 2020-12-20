package com.example.novigrad30.TestPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.novigrad30.R;

public class Test_Activity extends AppCompatActivity {

    private Button btnImage;
    private View draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ztest_);

        btnImage = (Button) findViewById(R.id.ImBtn);


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}