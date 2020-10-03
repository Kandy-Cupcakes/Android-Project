package com.cupcakes.kandycupcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cupcakes.kandycupcakes.IT19152110.kas;
import com.cupcakes.kandycupcakes.IT19207100.ImagesActivity;
import com.cupcakes.kandycupcakes.IT19210520.allpayment;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        Button add = findViewById(R.id.addvehi);
        Button faq = findViewById(R.id.addfq);
        Button pay = findViewById(R.id.paymentbtn);
        Toolbar toolbar = findViewById(R.id.toolbar);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Admin.this, ImagesActivity.class);
                startActivity(in);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Admin.this, kas.class);
                startActivity(in);
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Admin.this, allpayment.class);
                startActivity(in);
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.logout){

            Toast.makeText(getApplicationContext(),"You Logged Out",Toast.LENGTH_SHORT).show();

            Intent in = new Intent(Admin.this, LoginActivity.class);
            startActivity(in);

        }

        return super.onOptionsItemSelected(item);
    }




}