package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // from the city name we infer/ get the intent 
        Intent intent = getIntent();
        String city_name = intent.getStringExtra("city_name");

        // now we go ahead and display the city name
        TextView city_name_text = findViewById(R.id.city_name_text);
        city_name_text.setText(city_name);

        //now setting up the back button 
        Button bck_btn = findViewById(R.id.bck_btn);
        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
                finish(); //to basically close the show activity and return to the main stuff
            }
        });
    }
}

