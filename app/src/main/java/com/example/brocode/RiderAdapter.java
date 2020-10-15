package com.example.brocode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RiderAdapter extends RecyclerView.Adapter<RiderAdapter.riderViewHolder> {

    private Context mCtx;
    private List<Riders> riderList;
    DatabaseReference dbRef;
    Order order;





    public RiderAdapter(Context mCtx, List<Riders> riderList,Order order) {
        this.mCtx = mCtx;
        this.riderList = riderList;
        this.order=order;
    }
    @NonNull
    @Override
    public riderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_rider, parent, false);
        return new RiderAdapter.riderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final riderViewHolder holder, int position) {
        final Riders rider=riderList.get(position);
        holder.name.setText(rider.Rname);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Delivery");
                Delivery delivery=new Delivery();
                delivery.setOrderID(order.getId());
                delivery.setRiderID(rider.getRid());

                dbRef.child(order.getId()).setValue(delivery).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

 DatabaseReference dbRef2=FirebaseDatabase.getInstance().getReference().child("Order");
 order.setStatus("AS");
 dbRef2.child(order.getId()).setValue(order);
Intent intent4=new Intent(holder.add.getContext(),AssignRider.class);
intent4.putExtra("order",order);
intent4.putExtra("rider",rider.getRname());
holder.add.getContext().startActivity(intent4);
                        }
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return riderList.size();
    }

    class riderViewHolder extends RecyclerView.ViewHolder{
TextView name;
Button add;
RelativeLayout parentlayout;


        public riderViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.riderName);
            add=itemView.findViewById(R.id.addBtn);
        }
    }
}
