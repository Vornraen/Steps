package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("intname");
        DBHelper dbHelper = new DBHelper(this);

        int calorieCount = dbHelper.calorieCounter(username);
        TextView totalCalories = (TextView)findViewById(R.id.textView5);
        System.out.println(calorieCount);
        totalCalories.setText(String.valueOf(calorieCount));

        TextView foodList = (TextView)findViewById(R.id.card3);
        foodList.setText("");
        for (String food : dbHelper.foodList(username))
            foodList.append(food + "\n");

        TextView activityList = (TextView)findViewById(R.id.card4);
        activityList.setText("");
        for (String activity : dbHelper.activityList(username))
            activityList.append(activity + "\n");

        Button btn2, btn3;
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("intname", username);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("intname", username);
                startActivity(intent);
            }
        });

    }
}