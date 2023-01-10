package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddFood extends AppCompatActivity {
    Spinner spinner;
    DBHelper dbHelper;
    Button back,submit;
    EditText grams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("intname");

        dbHelper = new DBHelper(this);
        spinner = (Spinner) findViewById(R.id.SpinnerF);
        grams = (EditText)findViewById(R.id.Grame);
        back = (Button)findViewById(R.id.back);
        submit = (Button)findViewById(R.id.submitbtn);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dbHelper.getFoodList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), AddActivity.class);

                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addFood(username, spinner.getSelectedItem().toString(), grams.getText().toString());
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("intname", username);
                startActivity(intent);
            }
        });



    }

}
