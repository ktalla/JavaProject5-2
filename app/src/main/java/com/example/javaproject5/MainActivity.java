package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton specialtyPizzas;
    ImageButton buildYourOwn;
    ImageButton yourCart;
    ImageButton storeOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        specialtyPizzas = (ImageButton) findViewById(R.id.specialtyPizzasButton);
        buildYourOwn = (ImageButton) findViewById(R.id.buildYourOwnButton);
        yourCart = (ImageButton) findViewById(R.id.yourCartButton);
        storeOrders = (ImageButton) findViewById(R.id.storeOrdersButton);

        specialtyPizzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SpecialtyPizzas.class);
                startActivity(intent);
            }
        });

        buildYourOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, BuildYourOwn.class);
                startActivity(intent);
            }
        });

        yourCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, YourCart.class);
                startActivity(intent);
            }
        });

        storeOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(intent);
            }
        });







    }
}