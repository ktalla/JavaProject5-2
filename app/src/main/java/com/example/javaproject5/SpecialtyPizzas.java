package com.example.javaproject5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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

    private Spinner spinner;
    private static final String[] specialPizzas = {"Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"};
    private ImageView pizzaImage;
    private String pizzaName;
    private RadioGroup sizeGroup;
    private Pizza selectedPizza;
    private RadioGroup sauceGroup;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private ListView listView;
    private ArrayList<Topping> toppings;
    private ArrayAdapter<Topping> arrayAdapter;
    private Singleton singleton = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_pizzas);

        spinner = findViewById(R.id.pizzaTypes);
        listView = findViewById(R.id.toppingsListView);
        pizzaImage = findViewById(R.id.specialPizzaImage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpecialtyPizzas.this, android.R.layout.simple_spinner_item, specialPizzas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        pizzaImage.setImageResource(R.drawable.deluxe);
                        pizzaName = specialPizzas[0];
                        toppings = DeluxePizza.getStandardToppings();
                        arrayAdapter = new ArrayAdapter<Topping>(SpecialtyPizzas.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, toppings);
                        listView.setAdapter(arrayAdapter);
                        break;
                    case 1:
                        pizzaImage.setImageResource(R.drawable.supreme);
                        pizzaName = specialPizzas[1];
                        toppings = SupremePizza.getStandardToppings();
                        arrayAdapter = new ArrayAdapter<Topping>(SpecialtyPizzas.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, toppings);
                        listView.setAdapter(arrayAdapter);
                        break;
                    case 2:
                        pizzaImage.setImageResource(R.drawable.meatzza);
                        pizzaName = specialPizzas[2];
                        toppings = MeatzzaPizza.getStandardToppings();
                        arrayAdapter = new ArrayAdapter<Topping>(SpecialtyPizzas.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, toppings);
                        listView.setAdapter(arrayAdapter);
                        break;
                    case 3:
                        pizzaImage.setImageResource(R.drawable.seafood);
                        pizzaName = specialPizzas[3];
                        toppings = SeafoodPizza.getStandardToppings();
                        arrayAdapter = new ArrayAdapter<Topping>(SpecialtyPizzas.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, toppings);
                        listView.setAdapter(arrayAdapter);
                        break;
                    case 4:
                        pizzaImage.setImageResource(R.drawable.pepperoni);
                        pizzaName = specialPizzas[4];
                        toppings = PepperoniPizza.getStandardToppings();
                        arrayAdapter = new ArrayAdapter<Topping>(SpecialtyPizzas.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, toppings);
                        listView.setAdapter(arrayAdapter);
                        break;
                }
                PizzaMaker pizzaMaker = new PizzaMaker();
                selectedPizza = pizzaMaker.createPizza(pizzaName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button
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
               if(selectedPizza.getSize()==null || selectedPizza.getSauce()==null || spinner.getSelectedItem()==null){
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