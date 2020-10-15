package com.example.brocode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class displayRider extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RiderAdapter adapter;
    private List<Riders> riderList;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rider);
        Intent intent=getIntent();
        Order order=intent.getParcelableExtra("id");

        recyclerView = findViewById(R.id.recyclerView_rider);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        riderList = new ArrayList<>();
        adapter = new RiderAdapter(this, riderList,order);
        recyclerView.setAdapter(adapter);

        dbRef= FirebaseDatabase.getInstance().getReference().child("Rider");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        Riders riders=dataSnapshot1.getValue(Riders.class);
                        riderList.add(riders);

                    }
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}