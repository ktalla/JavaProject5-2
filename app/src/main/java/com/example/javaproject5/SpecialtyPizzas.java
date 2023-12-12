package com.example.javaproject5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
/**
 * Class that has SpecialtyPizzas and allows User to add them to their order
 * @author Keerthana Talla
 */

public class SpecialtyPizzas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Singleton singleton = Singleton.getInstance();
    private RadioGroup sizeGroup;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private Pizza selectedPizza;
    private TextView price;
    private EditText quantity;
    private int numOfPizzas =1;
    private boolean programmaticClear = false; //checks if user disselected or application disselected

    /**
     * Method to control all activity on page
     * @param savedInstanceState based on user input
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_pizzas);
        selectedPizza = singleton.getPizza();
        recyclerView = findViewById(R.id.recyclerView);
        sizeGroup=findViewById(R.id.sizeGroup);
        quantity = findViewById(R.id.editTextNumber);
        quantity.setText("1");
        quantity.setEnabled(false);
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
        extraSauce = findViewById(R.id.extraSauce);
        extraCheese = findViewById(R.id.extraCheese);
        handleQuantityAction();
        handleSizeGroupAction();
        handleExtraSauceAction();
        handleExtraCheeseAction();
       placeOrder = findViewById(R.id.specialtyOrderButton);
       handlePlaceOrderAction();
    }

    /**
     * Method that allows users to change quantity and changes price
     */
    private void handleQuantityAction(){
        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!String.valueOf(editable).isEmpty()) {
                    numOfPizzas = Integer.parseInt(String.valueOf(editable));
                    price.setText(String.format("%.2f", numOfPizzas*selectedPizza.price()));
                }
                else {
                    numOfPizzas = 1;
                    price.setText(String.format("%.2f", selectedPizza.price()));
                }
            }
        });
    }

    /**
     * Method that allows users to select size and changes price
     */
    private void handleSizeGroupAction(){
        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedPizza = singleton.getPizza();
                if(selectedPizza!=null) {
                    RadioButton selectedSize = group.findViewById(checkedId);
                    String selectedText = selectedSize.getText().toString();
                    selectedPizza.setSize(selectedText);
                    price.setText(String.format("%.2f", selectedPizza.price()));
                    quantity.setEnabled(true);
                }
                else{
                    if (!programmaticClear) {
                        programmaticClear = true;
                        group.clearCheck();
                        programmaticClear = false;
                    }
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
    }

    /**
     * Method that allows users to get extra sauce and changes price
     */
    private void handleExtraSauceAction(){
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
                    extraSauce.setChecked(false);
                }
            }
        });
    }

    /**
     * Method that allows users to get extra cheese and changes price
     */
    private void handleExtraCheeseAction(){
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
                    extraCheese.setChecked(false);
                }
            }
        });
    }

    /**
     * Method that allows users to place orders and updates singleton
     */
    private void handlePlaceOrderAction(){
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPizza = singleton.getPizza();
                if(selectedPizza==null || selectedPizza.getSize()==null){ //null pointer if immedaitely click on place order
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
                    for(int i=1; i<=numOfPizzas; i++) {
                        order.addPizza(selectedPizza);
                    }
                    singleton.setOrder(order);
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
