package com.cupcakes.kandycupcakes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cashPayment extends AppCompatActivity {

    TextView txtnic,txttotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_payment);

        Intent intent = getIntent();
        String nic = intent.getStringExtra("nic");
        String total = intent.getStringExtra("total");

        txtnic = (TextView) findViewById(R.id.nic2);
        txttotal = (TextView) findViewById(R.id.total2);

        txtnic.setText(nic);
        txttotal.setText(total);


    }
}