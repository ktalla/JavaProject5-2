package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class YourCart extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView orderNumber;
    TextView subTotal;
    TextView salesTax;
    TextView orderTotal;
    private int orderNum; //number integer of current order
    private ArrayList<Pizza> pizzas; //array list for all pizzas in current order
    private static final double TAXRATE = 0.06625; //tax rate for every order



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        orderNumber = findViewById(R.id.orderNumberText);
        subTotal = findViewById(R.id.subTotalPriceText);
        salesTax = findViewById(R.id.salesTaxPriceText);
        orderTotal = findViewById(R.id.orderTotalPriceText);



    }




}