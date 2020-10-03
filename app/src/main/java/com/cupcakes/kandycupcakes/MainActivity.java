package com.cupcakes.kandycupcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cupcakes.kandycupcakes.IT19152110.customerView;
import com.cupcakes.kandycupcakes.IT19210698.availablevehicles;

public class MainActivity extends AppCompatActivity {

    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.viewfaq);
        Button login = findViewById(R.id.btnadminlogin);
        Button bookvehical = findViewById(R.id.book);
        Button pay = findViewById(R.id.paymentbtn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(MainActivity.this, customerView.class);
                startActivity(in);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        bookvehical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, availablevehicles.class);
                startActivity(intent);
            }
        });



    }
}