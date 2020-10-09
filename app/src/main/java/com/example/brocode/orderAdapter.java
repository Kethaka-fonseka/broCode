package com.example.brocode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.orderViewHolder> {


    private Context mCtx;
    private List<Order> orderList;
    DatabaseReference dbRef;





    public orderAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_order, parent, false);
        return new orderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final orderViewHolder holder, final int position) {
final Order order=orderList.get(position);
holder.textViewID.setText(String.valueOf(order.getId()));
holder.textViewAddress.setText(order.getAddress());
holder.parentlayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(holder.parentlayout.getContext(),AssignRider.class);
        intent.putExtra("order", orderList.get(position));
        holder.parentlayout.getContext().startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    class orderViewHolder extends RecyclerView.ViewHolder{

        TextView textViewID,textViewAddress;
        RelativeLayout parentlayout;

        public orderViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID=itemView.findViewById(R.id.orderID);
            textViewAddress=itemView.findViewById(R.id.orderAddress);
            parentlayout=itemView.findViewById(R.id.parent_layout);


        }
    }
}
