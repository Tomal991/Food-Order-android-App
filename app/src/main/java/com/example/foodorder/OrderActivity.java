package com.example.foodorder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OrderActivity extends AppCompatActivity {

    EditText foodName, priceTK;
    RadioGroup myradiogroup;
    RadioButton myradiobutton;
    Button order_button;
    String food, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        foodName = findViewById(R.id.food_nameID);
        priceTK = findViewById(R.id.priceID);
        myradiogroup = findViewById(R.id.myradiogroup);
        order_button = findViewById(R.id.order_button);
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(OrderActivity.this);
                food = foodName.getText().toString().trim();
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
}
