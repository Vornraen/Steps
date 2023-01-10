package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    Button alimente, exercitii, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        Intent intent = getIntent();
        String username = intent.getStringExtra("intname");

        alimente = (Button) findViewById(R.id.alimente);
        exercitii = (Button) findViewById(R.id.munca);
        back = (Button) findViewById(R.id.back);

        alimente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), AddFood.class);
                intent.putExtra("intname", username);
                startActivity(intent);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        exercitii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), AddWork.class);
                intent.putExtra("intname", username);

                startActivity(intent);
            }
        });
    }
    }