package com.cupcakes.kandycupcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cupcakes.kandycupcakes.IT19207100.ImagesActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final   EditText  username =(EditText) findViewById(R.id.txtusername);
        final EditText  pasword = (EditText)findViewById(R.id.txtpassword);

        Button log = findViewById(R.id.logbtn);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user = username.getText().toString();

                final String p = pasword.getText().toString();




                    validate(user,p);


        }


    });
    }

    public void validate(String username, String password) {

        if ((username.equals("cup")) && (password.equals("1234"))) {

            Intent i = new Intent(LoginActivity.this, Admin.class);
            startActivity(i);

        }
        else if(!username.equals("")&&!username.equals("cup")){
            Toast.makeText(LoginActivity.this, "Please enter correct username", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals("")&&!password.equals("1234")){
            Toast.makeText(LoginActivity.this, "Please enter correct password", Toast.LENGTH_SHORT).show();
        }
        else if(!username.equals("")&&!username.equals("cup")&&!password.equals("")&&!password.equals("cup")){
            Toast.makeText(LoginActivity.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
        }

        else if(!username.equals("")&&username.equals("cup")&&password.equals("")){
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
        }

        else {
                Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();

        }
    }
}