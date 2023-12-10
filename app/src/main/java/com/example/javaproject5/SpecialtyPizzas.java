package com.example.javaproject5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;

public class SpecialtyPizzas extends AppCompatActivity {


    //private static ArrayList<String> specialPizzas = new ArrayList<>(Arrays.asList("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"));
    private RecyclerView recyclerView;
    private Singleton singleton = Singleton.getInstance();
    private RadioGroup sizeGroup;
    private RadioGroup sauceGroup;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private Pizza selectedPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_pizzas);
        selectedPizza = singleton.getPizza();
        recyclerView = findViewById(R.id.recyclerView);
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Deluxe",DeluxePizza.getStandardToppings(), R.drawable.deluxe));
        items.add(new Item("Supreme",SupremePizza.getStandardToppings(), R.drawable.supreme));
        items.add(new Item("Meatzza",MeatzzaPizza.getStandardToppings(), R.drawable.meatzza));
        items.add(new Item("Pepperoni",PepperoniPizza.getStandardToppings(), R.drawable.pepperoni));
        items.add(new Item("Seafood",SeafoodPizza.getStandardToppings(), R.drawable.seafood));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SpecialtyPizzaAdapter(getApplicationContext(),items));


        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button
                selectedPizza = singleton.getPizza();
                RadioButton selectedSize = group.findViewById(checkedId);
                String selectedText = selectedSize.getText().toString();
                selectedPizza.setSize(selectedText);

            }
        });

        sauceGroup = findViewById(R.id.sauceGroup);
        sauceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button
                selectedPizza = singleton.getPizza();
                RadioButton selectedSauce = group.findViewById(checkedId);
                String selectedText = selectedSauce.getText().toString();
                selectedPizza.setSauce(selectedText);

            }
        });

        extraSauce = findViewById(R.id.extraSauce);
        extraCheese = findViewById(R.id.extraCheese);
        extraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               selectedPizza = singleton.getPizza();
               if(compoundButton.isChecked()){
                   selectedPizza.setExtraSauce(true);
               }
               else{
                   selectedPizza.setExtraSauce(false);
               }
           }
       });

       extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               selectedPizza = singleton.getPizza();
               if(compoundButton.isChecked()){
                   selectedPizza.setExtraCheese(true);
               }
               else{
                   selectedPizza.setExtraCheese(false);
               }
           }
       });

       placeOrder = findViewById(R.id.specialtyOrderButton);
       placeOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               selectedPizza = singleton.getPizza();
               if(selectedPizza.getSize()==null || selectedPizza.getSauce()==null){
                   AlertDialog.Builder builder = new AlertDialog.Builder(SpecialtyPizzas.this);
                   builder.setMessage("Missing information");
                   builder.setTitle("Alert !");
                   builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                       dialog.cancel();
                   });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.show();
               }
               else{

               Order order = singleton.getOrder();
               if (order == null) {
                   order = new Order(new ArrayList<Pizza>());
                   singleton.setOrder(order);
               }
               order.addPizza(selectedPizza);
               singleton.setOrder(order);
               //Log.d("Success", singleton.getOrder().toString());

               AlertDialog.Builder builder = new AlertDialog.Builder(SpecialtyPizzas.this);
               builder.setMessage("Pizza Ordered!");
               builder.setTitle("Success! Order Number: " + singleton.getCurrentOrderNum());
               builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                   dialog.cancel();
               });
               AlertDialog alertDialog = builder.create();
               alertDialog.show();
           }
           }
       });

    }
}
