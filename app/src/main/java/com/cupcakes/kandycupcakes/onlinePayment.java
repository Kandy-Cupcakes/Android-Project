package com.cupcakes.kandycupcakes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class onlinePayment extends AppCompatActivity {

    TextView txtnic,txttotal,txtbdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);

        Intent intent = getIntent();
        String nic = intent.getStringExtra("nic");
        String total = intent.getStringExtra("total");
        String bdate = intent.getStringExtra("bookingd");

        txtnic = (TextView) findViewById(R.id.nic);
        txttotal = (TextView) findViewById(R.id.total);
        txtbdate = (TextView) findViewById(R.id.textViewbdate);
        txtnic.setText(nic);
        txttotal.setText(total);
        txtbdate.setText(bdate);
    }
}