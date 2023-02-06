package comp3350.quickkitchen.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.quickkitchen.R;

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
