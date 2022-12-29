package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String username = intent.getStringExtra("intname");
        DBHelper dbHelper = new DBHelper(this);

        int calorieCount = dbHelper.calorieCounter(username);

        TextView totalCalories = (TextView)findViewById(R.id.kcal);
        totalCalories.setText(calorieCount+" KC");

        TextView foodList = (TextView)findViewById(R.id.card1);
        foodList.setText("");
        for (String food : dbHelper.foodList(username))
            foodList.append(food + "\n");
    }
}