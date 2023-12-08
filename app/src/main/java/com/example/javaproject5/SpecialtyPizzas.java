package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpecialtyPizzas extends AppCompatActivity {

    private Spinner spinner;
    private static final String[] specialPizzas = {"Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"};
    private ImageView pizzaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_pizzas);

        spinner = findViewById(R.id.pizzaTypes);
        pizzaImage = findViewById(R.id.specialPizzaImage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpecialtyPizzas.this, android.R.layout.simple_spinner_item, specialPizzas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        pizzaImage.setImageResource(R.drawable.deluxe);
                        break;
                    case 1:
                        pizzaImage.setImageResource(R.drawable.supreme);
                        break;
                    case 2:
                        pizzaImage.setImageResource(R.drawable.meatzza);
                        break;
                    case 3:
                        pizzaImage.setImageResource(R.drawable.seafood);
                        break;
                    case 4:
                        pizzaImage.setImageResource(R.drawable.pepperoni);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}