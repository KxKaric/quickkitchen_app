package comp3350.quickkitchen.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.features.ShowSteps;

public class InstructionsActivity extends AppCompatActivity {
    private ShowSteps showSteps;
    private List<String> stepsList;
    private ArrayAdapter<String> stepsArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        String chosen;
        try {
            chosen = intent.getStringExtra("chosenRecipe");
            if(chosen !=null){
                showSteps = new ShowSteps();
                stepsList = new ArrayList<>();
                stepsList=showSteps.showSteps(chosen);
            }
            final ListView listView = (ListView) findViewById(R.id.instrutions_list);
            stepsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stepsList);
            listView.setAdapter(stepsArrayAdapter);
            //System.out.println("Chosen  = : " + chosen);

            TextView textView = findViewById(R.id.recipeName);
            textView.setText(chosen);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }



    }
}
