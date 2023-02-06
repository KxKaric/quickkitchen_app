package com.example.quickkitchen.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quickkitchen.R;

public class Instructions extends AppCompatActivity {
    //Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        String chosen = intent.getStringExtra("chosenRecipe");

        System.out.println("Chosen  = : " + chosen);

        TextView textView = findViewById(R.id.recipeName);
        textView.setText(chosen);
    }
}
