package com.cupcakes.kandycupcakes;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class availablevehicles extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<vehicles> vehicleList;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablevehicles);

        ref = FirebaseDatabase.getInstance().getReference().child("uploads");
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        searchView = findViewById(R.id.searchview);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ref != null){

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        vehicleList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()){
                            vehicles vhcl = ds.getValue(vehicles.class);
                            vhcl.setKey(ds.getKey());
                            vehicleList.add(vhcl);
                        }
                        vehiAdapter vehiadapter = new vehiAdapter(getApplicationContext(),vehicleList);
                        recyclerView.setAdapter(vehiadapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(availablevehicles.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }



    public void search(String str){
        ArrayList<vehicles> mylist = new ArrayList<>();
        for(vehicles object : vehicleList){
            if (object.getName().toLowerCase().contains(str.toLowerCase())){
                mylist.add(object);
            }
        }
        vehiAdapter vehiAdapter = new vehiAdapter(getApplicationContext(),mylist);
        recyclerView.setAdapter(vehiAdapter);
    }

}