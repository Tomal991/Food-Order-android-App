package com.example.foodorder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] foodarr = {"Select", "Cheese Burger", "Beef Burger", "Large Pizza", "Kacchi", "Sandwich"};

    Spinner myspinner;
    EditText priceTK;
    RadioGroup myradiogroup;
    RadioButton myradiobutton;
    Button order_button;
    String food, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        myspinner = (Spinner) findViewById(R.id.food_nameID);
        myspinner.setOnItemSelectedListener(this);

        ArrayAdapter array = new ArrayAdapter(this, android.R.layout.simple_spinner_item, foodarr);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(array);

        priceTK = findViewById(R.id.priceID);
        myradiogroup = findViewById(R.id.myradiogroup);
        order_button = findViewById(R.id.order_button);
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(OrderActivity.this);
                food = myspinner.getSelectedItem().toString();
                price = priceTK.getText().toString().trim();
                int radiobuttonid = myradiogroup.getCheckedRadioButtonId();
                myradiobutton = findViewById(radiobuttonid);
                quantity = myradiobutton.getText().toString().trim();

                myDB.addOrder(food, price, quantity);
                Intent i = new Intent(OrderActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        if (text.equals("Cheese Burger")) {
            priceTK.setText("150");
        }
        if (text.equals("Beef Burger")) {
            priceTK.setText("200");
        }
        if (text.equals("Large Pizza")) {
            priceTK.setText("500");
        }
        if (text.equals("Kacchi")) {
            priceTK.setText("400");
        }
        if (text.equals("Sandwich")) {
            priceTK.setText("100");
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
