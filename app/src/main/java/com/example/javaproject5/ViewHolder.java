package com.example.javaproject5;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewHolder extends RecyclerView.ViewHolder {

//    private static final String[] specialPizzas = {"Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"};
     ImageView pizzaImage;
     TextView pizzaTitle;
     TextView toppingsList;
//     Pizza selectedPizza;
//     ArrayList<Topping> toppings;
     ArrayAdapter<Topping> arrayAdapter;
//     Singleton singleton = Singleton.getInstance();


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaImage = itemView.findViewById(R.id.imageview);
        pizzaTitle = itemView.findViewById(R.id.pizzaName);
        toppingsList = itemView.findViewById(R.id.toppingsList);

    }

    public void setToppings(List<Topping> toppings) {
        StringBuilder toppingsText = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsText.append(topping.getName(topping)).append(", ");
        }

        if (toppingsText.length() > 0) {
            toppingsText.delete(toppingsText.length() - 2, toppingsText.length());
        }

        toppingsList.setText(toppingsText.toString());
    }

}
