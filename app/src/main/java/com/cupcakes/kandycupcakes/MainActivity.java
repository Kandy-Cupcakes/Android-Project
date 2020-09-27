package com.cupcakes.kandycupcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button buttonA;
    private Button buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonA = findViewById(R.id.faqBtn);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKas();
            }
        });

        buttonC = findViewById(R.id.viewBtn);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCus();
            }
        });
    }
    public void openKas() {
        Intent intent = new Intent(this,kas.class);
        startActivity(intent);
    }

    public void openCus() {
        Intent intent = new Intent(this, customerView.class);
        startActivity(intent);
    }
}