package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
int quantity = 0;
Spinner spinner;
ArrayList spinnerArrayList;
ArrayAdapter spinnerAdapter;

HashMap goodsMap;
String goodsName;
double price;
EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);

        goodsMap = new HashMap();
        goodsMap.put("guitar",500.0);
        goodsMap.put("drums",1500.0);
        goodsMap.put("keyboard",1000.0);

    }

    public void plusUnit(View view) {
        quantity = quantity + 1;
        TextView quantityCost = findViewById(R.id.quantityCost);
        quantityCost.setText(" " + quantity);
        TextView priceTextView = findViewById(R.id.zeroCost);
        priceTextView.setText("" + quantity * price);

    }

    public void minCuant(View view) {
        quantity = quantity - 1;
        if(quantity < 0){

            quantity = 0;
        }
        TextView quantityCost = findViewById(R.id.quantityCost);
        quantityCost.setText(" " + quantity);

        TextView priceTextView = findViewById(R.id.zeroCost);
        priceTextView.setText("" + quantity * price);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       goodsName = spinner.getSelectedItem().toString();
       price = (double)goodsMap.get(goodsName);
       TextView priceTextView = findViewById(R.id.zeroCost);
       priceTextView.setText("" + quantity * price);
        ImageView goodsImageView = findViewById(R.id.imageView);
        if (goodsName.equals("guitar")) {
            goodsImageView.setImageResource(R.drawable.guitar);
        } else if (goodsName.equals("drums")){
            goodsImageView.setImageResource(R.drawable.drums);
        }else if (goodsName.equals("keyboard")){
            goodsImageView.setImageResource(R.drawable.keyboard);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
order.userName = userNameEditText.getText().toString();
order.goodsName = goodsName;
order.quantity = quantity; 
order.orderPrice = quantity * price;
order.price = price;
Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
        String name;
      orderIntent.putExtra("userNameForIntent",order.userName);
      orderIntent.putExtra("goodsName",order.goodsName);
      orderIntent.putExtra("quantity",order.quantity);
      orderIntent.putExtra("price",order.price);
      orderIntent.putExtra("orderPrice",order.orderPrice);

startActivity(orderIntent);

    }
}