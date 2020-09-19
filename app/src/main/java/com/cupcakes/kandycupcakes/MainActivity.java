package com.cupcakes.kandycupcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cupcakes.kandycupcakes.IT19207100.addvehical;

public class MainActivity extends AppCompatActivity {

    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.btnadd);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(MainActivity.this, addvehical.class);
                startActivity(in);
            }
        });

    }
}