package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    String user, pass;
    MyDatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameID);
        password = findViewById(R.id.passwordID);
        mydb = new MyDatabaseHelper(this);
    }

    public void login(View view) {
        user = username.getText().toString();
        pass = password.getText().toString();

        if (username.equals("") || password.equals("")){
            Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean checkuserpass = mydb.checkUsernamePassword(user, pass);
            if(checkuserpass == true){
                Toast.makeText(MainActivity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
            else {
                Toast.makeText(MainActivity.this, "User not exists", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registerPage(View view) {
        Intent i = new Intent(MainActivity.this, Registration.class);
        startActivity(i);
    }
}
