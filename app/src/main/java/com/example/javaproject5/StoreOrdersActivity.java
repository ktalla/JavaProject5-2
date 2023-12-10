package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity {
    private Button cancelOrder;
    private ListView listview;
    private TextView price;
    private Spinner spinner;
    private ArrayList<Integer> orderNum = new ArrayList<>();
    private Singleton singleton = Singleton.getInstance();
    ArrayAdapter<Pizza> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        orderNum.clear();
        if(singleton.getStoreOrders()!=null && singleton.getStoreOrders().getOrders()!=null) {
            for (int i = 0; i < singleton.getStoreOrders().getOrders().size(); i++) {
                orderNum.add(singleton.getStoreOrders().getOrders().get(i).getOrderNumber() + 1);
            }
        }

        cancelOrder = findViewById(R.id.cancelOrderButton);
        listview = findViewById(R.id.storeOrdersListView);
        arrayAdapter = new ArrayAdapter<Pizza>(StoreOrdersActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        listview.setAdapter(arrayAdapter);
        price = findViewById(R.id.orderTotalPrice);
        spinner = findViewById(R.id.spinnerOrder);
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = spinner.getSelectedItemPosition()+1;
                if(index<0)
                    return;
                StoreOrders storeOrders = singleton.getStoreOrders();
                List<Order> orders = storeOrders.getOrders();
                for(Order order: orders){
                    Log.d("Order", "index: "+ index + " " + order.getOrderNumber());
                    if (order.getOrderNumber()+1 == index) {
                        storeOrders.remove(order);
                        break;
                    }
                }

                singleton.setStoreOrders(storeOrders);
                arrayAdapter = new ArrayAdapter<Pizza>(StoreOrdersActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
                listview.setAdapter(arrayAdapter);
                orderNum.clear();
                if(singleton.getStoreOrders()!=null && singleton.getStoreOrders().getOrders()!=null) {
                    for (int i = 0; i < singleton.getStoreOrders().getOrders().size(); i++) {
                        orderNum.add(singleton.getStoreOrders().getOrders().get(i).getOrderNumber() + 1);
                    }
                }

            }
        });

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(StoreOrdersActivity.this, android.R.layout.simple_spinner_item, orderNum);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                int index = spinner.getSelectedItemPosition();
                StoreOrders storeOrders = singleton.getStoreOrders();
                Order order = storeOrders.getOrders().get(index);
                arrayAdapter = new ArrayAdapter<Pizza>(StoreOrdersActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, order.getPizzas());
                listview.setAdapter(arrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}