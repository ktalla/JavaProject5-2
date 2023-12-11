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
import android.widget.TextView;

import java.util.ArrayList;

public class SpecialtyPizzas extends AppCompatActivity {


    //private static ArrayList<String> specialPizzas = new ArrayList<>(Arrays.asList("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"));
    private RecyclerView recyclerView;
    private Singleton singleton = Singleton.getInstance();
    private RadioGroup sizeGroup;

    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private Pizza selectedPizza;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_pizzas);
        selectedPizza = singleton.getPizza();
        recyclerView = findViewById(R.id.recyclerView);
        sizeGroup=findViewById(R.id.sizeGroup);
        price = findViewById(R.id.textViewPrice);
        price.setText(String.format("%.2f", 0.00));
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Deluxe",DeluxePizza.getStandardToppings(), R.drawable.deluxe, Sauce.TOMATO));
        items.add(new Item("Supreme",SupremePizza.getStandardToppings(), R.drawable.supreme, Sauce.TOMATO));
        items.add(new Item("Meatzza",MeatzzaPizza.getStandardToppings(), R.drawable.meatzza, Sauce.TOMATO));
        items.add(new Item("Pepperoni",PepperoniPizza.getStandardToppings(), R.drawable.pepperoni, Sauce.TOMATO));
        items.add(new Item("Seafood",SeafoodPizza.getStandardToppings(), R.drawable.seafood, Sauce.ALFREDO));
        items.add(new Item("Baddie",BaddiePizza.getStandardToppings(), R.drawable.baddie, Sauce.TOMATO));
        items.add(new Item("VeggieLovers",VeggieLoversPizza.getStandardToppings(), R.drawable.veggielovers, Sauce.TOMATO));
        items.add(new Item("Grandma",GrandmaPizza.getStandardToppings(), R.drawable.grandma, Sauce.ALFREDO));
        items.add(new Item("Loverboy",LoverboyPizza.getStandardToppings(), R.drawable.loverboy, Sauce.TOMATO));
        items.add(new Item("CardiP",CardiPPizza.getStandardToppings(), R.drawable.cardip, Sauce.ALFREDO));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SpecialtyPizzaAdapter(getApplicationContext(),items));



        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedPizza = singleton.getPizza();
                if(selectedPizza!=null) {
                    RadioButton selectedSize = group.findViewById(checkedId);
                    String selectedText = selectedSize.getText().toString();
                    selectedPizza.setSize(selectedText);
                    price.setText(String.format("%.2f", selectedPizza.price()));
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SpecialtyPizzas.this);
                    builder.setMessage("Select a pizza!");
                    builder.setTitle("Alert !");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });




        extraSauce = findViewById(R.id.extraSauce);
        extraCheese = findViewById(R.id.extraCheese);
        extraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               selectedPizza = singleton.getPizza();
               if(selectedPizza!=null && selectedPizza.getSize()!=null) {
                   if (compoundButton.isChecked()) {
                       selectedPizza.setExtraSauce(true);
                       price.setText(String.format("%.2f", selectedPizza.price()));
                   } else {
                       selectedPizza.setExtraSauce(false);
                       price.setText(String.format("%.2f", selectedPizza.price()));
                   }
               }
               else{
                   AlertDialog.Builder builder = new AlertDialog.Builder(SpecialtyPizzas.this);
                   builder.setMessage("Select a Pizza and a size!");
                   builder.setTitle("Alert !");
                   builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                       dialog.cancel();
                   });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.show();
               }
           }
       });

       extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               selectedPizza = singleton.getPizza();
               if(selectedPizza!=null && selectedPizza.getSize()!=null) {
                   if (compoundButton.isChecked()) {
                       selectedPizza.setExtraCheese(true);
                       price.setText(String.format("%.2f", selectedPizza.price()));
                   } else {
                       selectedPizza.setExtraCheese(false);
                       price.setText(String.format("%.2f", selectedPizza.price()));
                   }
               }
               else{
                   AlertDialog.Builder builder = new AlertDialog.Builder(SpecialtyPizzas.this);
                   builder.setMessage("Select a Pizza and size!");
                   builder.setTitle("Alert !");
                   builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                       dialog.cancel();
                   });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.show();
               }
           }
       });

       placeOrder = findViewById(R.id.specialtyOrderButton);
       placeOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               selectedPizza = singleton.getPizza();
               if(selectedPizza==null && selectedPizza.getSize()==null){ //null pointer if immedaitely click on place order
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
