package com.cupcakes.kandycupcakes.IT19207100;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cupcakes.kandycupcakes.MainActivity;
import com.cupcakes.kandycupcakes.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;

    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private  ValueEventListener mDBListener;
    private List<Upload> mUploads;
    private Context mContext;
    public ImageView updimage;

    FloatingActionButton  fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images2);

        fa=findViewById(R.id.fab);
        updimage = findViewById(R.id.updateimage);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = findViewById(R.id.progresscycle);
        mUploads = new ArrayList<>();

        mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ImagesActivity.this);


        mStorage=FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDBListener =mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(ImagesActivity.this, addvehical.class);
                startActivity(in);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(ImagesActivity.this, "Normal click at position"+ position, Toast.LENGTH_SHORT).show();

     //   Upload up = new Upload();
        Upload selectedItem = mUploads.get(position);

        Intent in = new Intent(ImagesActivity.this,updatevehical.class);
        in.putExtra("name",selectedItem.getName());
       in.putExtra("photo",selectedItem.getImageUrl());
        in.putExtra("KEY",selectedItem.getKey());
        in.putExtra("passenger",selectedItem.getPassengers());
        in.putExtra("price",selectedItem.getPrice());
        in.putExtra("transmission",selectedItem.getTransmisson());
        startActivity(in);




        System.out.println(selectedItem.getImageUrl());
        System.out.println(selectedItem.getKey());
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(ImagesActivity.this, "you click at position"+position, Toast.LENGTH_SHORT).show();

        Upload selectedItem = mUploads.get(position);

        Intent in = new Intent(ImagesActivity.this,updatevehical.class);
        in.putExtra("name",selectedItem.getName());
        in.putExtra("photo",selectedItem.getImageUrl());
        in.putExtra("KEY",selectedItem.getKey());
        in.putExtra("passenger",selectedItem.getPassengers());
        in.putExtra("price",selectedItem.getPrice());
        in.putExtra("transmission",selectedItem.getTransmisson());
        startActivity(in);

    }

    @Override
    public void onDeleteClick(final int position) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to Delete this ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Upload selectedItem = mUploads.get(position);
                        final String selectedkey = selectedItem.getKey();
                        StorageReference imageref = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
                        imageref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mDatabaseRef.child(selectedkey).removeValue();
                                Toast.makeText(ImagesActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}