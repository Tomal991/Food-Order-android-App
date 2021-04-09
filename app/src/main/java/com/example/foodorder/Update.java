package com.example.foodorder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText food_input, price_input, quantity_input;
    Button update_button, delete_button;

    String id, fname, price, quan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        food_input = findViewById(R.id.food_nameID);
        price_input = findViewById(R.id.priceID);
        quantity_input = findViewById(R.id.quantityID);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(fname);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);
                fname = food_input.getText().toString().trim();
                price = price_input.getText().toString().trim();
                quan = quantity_input.getText().toString().trim();
                myDB.updateData(id, fname, price, quan);
                Intent i = new Intent(Update.this, MainActivity2.class);
                startActivity(i);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("fname") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("quan")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            fname = getIntent().getStringExtra("fname");
            price = getIntent().getStringExtra("price");
            quan = getIntent().getStringExtra("quan");

            //Setting Intent Data
            food_input.setText(fname);
            price_input.setText(price);
            quantity_input.setText(quan);
            Log.d("nhh", fname + " " + price + " " + quan);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + fname + " ?");
        builder.setMessage("Are you sure you want to delete " + fname + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
