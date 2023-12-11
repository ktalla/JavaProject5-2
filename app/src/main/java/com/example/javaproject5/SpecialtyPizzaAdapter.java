package com.example.javaproject5;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Adapter needed for RecyclerView
 * @author Keerthana Talla
 */

public class SpecialtyPizzaAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<Item> items;
    private Singleton singleton = Singleton.getInstance();

    public SpecialtyPizzaAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_specialty_pizza,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.pizzaTitle.setText(items.get(position).getSpecialtyPizzaName());
        List<Topping> toppings = items.get(position).getToppings();
        holder.setToppings(toppings, items.get(position).getSauce());
        //holder.toppingsList.setText(items.get(position).getToppings());
        holder.pizzaImage.setImageResource(items.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String pizzaName = (String) holder.pizzaTitle.getText();
               PizzaMaker pizzaMaker = new PizzaMaker();
               Pizza selectedPizza = pizzaMaker.createPizza(pizzaName);
               singleton.setPizza(selectedPizza);
               Toast toast = Toast.makeText(context.getApplicationContext(), pizzaName + " selected!", Toast.LENGTH_SHORT);
               toast.show();
               SpecialtyPizzas specialtyPizzas = new SpecialtyPizzas();
               specialtyPizzas.setQuantityToEditable();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

