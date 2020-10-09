package com.example.brocode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AssignRider extends AppCompatActivity {
TextView id,address;
EditText text;
Button confrim,assign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_rider);
     id=findViewById(R.id.orderIDview);
     confrim=findViewById(R.id.confirmBtn);
     text=findViewById(R.id.riderName);
     address=findViewById(R.id.orderAdressview);
     assign=findViewById(R.id.assignBtn);

        final Intent intent=getIntent();
        final Order order=intent.getParcelableExtra("order");

        Intent intent3=getIntent();
        String rider=intent.getStringExtra("rider");

        text.setText(rider);

        id.setText(order.getId());
        address.setText(order.getAddress());
     confrim.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(TextUtils.isEmpty(text.getText().toString().trim())){
                 text.setError("Please assign a rider");
             }
         }
     });
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),displayRider.class);
                intent1.putExtra("id",order.getId());
                startActivity(intent1);
            }
        });

    }
}