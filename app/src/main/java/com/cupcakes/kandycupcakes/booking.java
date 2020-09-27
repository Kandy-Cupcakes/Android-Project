package com.cupcakes.kandycupcakes;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class booking extends AppCompatActivity {

    EditText bdate,rdate,name,nic,mobile,vehicleid;
    Button btnNext;
    DatabaseReference reff;
    Bookings bookings;
    DatePickerDialog.OnDateSetListener setListener;
    long maxid=0;
    RadioButton cash,online;
    Integer selectedmethod;
    Double total;
    Date date1;
    Date date2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        name = (EditText) findViewById(R.id.name);
        nic = (EditText) findViewById(R.id.nic);
        mobile = (EditText) findViewById(R.id.mobile);
        bdate = (EditText) findViewById(R.id.bdate);
        rdate = (EditText) findViewById(R.id.rdate);
        btnNext = (Button) findViewById(R.id.btnNext);
        reff = FirebaseDatabase.getInstance().getReference().child("Bookings");
        bookings = new Bookings();
        cash = (RadioButton) findViewById(R.id.radio_one);
        online = (RadioButton) findViewById(R.id.radio_two);
        Intent newintent = getIntent();
        final String price =  newintent.getStringExtra("vehi_price");
        final String key = newintent.getStringExtra("key");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date1 = month+"/"+day+"/"+year;

                        bdate.setText(date1);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        rdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = month+"/"+day+"/"+year;
                        rdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                bookings.setName(name.getText().toString().trim());
                bookings.setNic(nic.getText().toString().trim());
                bookings.setMobile(mobile.getText().toString().trim());
                bookings.setVehicleid(key);
                bookings.setBookingDate(bdate.getText().toString().trim());
                bookings.setReturnDate(rdate.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(bookings);


                String CurrentDate= bdate.getText().toString();
                String FinalDate= rdate.getText().toString();

                @SuppressLint("SimpleDateFormat") SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    date1 = dates.parse(CurrentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date2 = dates.parse(FinalDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);
                String dayDifference = Long.toString(differenceDates);
                Double total = Double.parseDouble(price) * Integer.parseInt(dayDifference);


                if ( selectedmethod == 1){
                    Intent i1 = new Intent(getApplicationContext(),cashPayment.class);
                    i1.putExtra("nic",nic.getText().toString());
                    i1.putExtra("total",String.valueOf(total));
                    i1.putExtra("bookingd",bdate.getText().toString());
                    startActivity(i1);
                }
                else if(selectedmethod == 2){
                    Intent i2 = new Intent(getApplicationContext(),onlinePayment.class);
                    i2.putExtra("nic",nic.getText().toString());
                    i2.putExtra("total",String.valueOf(total));
                    i2.putExtra("bookingd",bdate.getText().toString());
                    startActivity(i2);
                }

            }
        });


    }
    public void checkButton1(View v){
        selectedmethod = 1;

    }
    public void checkButton2(View v){
        selectedmethod = 2;

    }


    }


