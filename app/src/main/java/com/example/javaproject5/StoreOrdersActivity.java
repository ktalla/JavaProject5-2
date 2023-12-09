package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    private Button cancelOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);

        cancelOrder = findViewById(R.id.cancelOrderButton);
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete the order we are on.
            }
        });
    }
}