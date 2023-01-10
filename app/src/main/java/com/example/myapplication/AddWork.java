package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddWork extends AppCompatActivity {
    Spinner spinner;
    DBHelper dbHelper;
    Button back,submit;
    EditText timp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        Intent intent = getIntent();
        String username = intent.getStringExtra("intname");

        dbHelper = new DBHelper(this);
        spinner = (Spinner) findViewById(R.id.SpinnerE);
        timp = (EditText)findViewById(R.id.timp);
        back = (Button)findViewById(R.id.back);
        submit = (Button)findViewById(R.id.submitbtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dbHelper.getWorkList());
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
                dbHelper.addWork(username, spinner.getSelectedItem().toString(), timp.getText().toString());
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("intname", username);
                startActivity(intent);
            }
        });

    }
}
