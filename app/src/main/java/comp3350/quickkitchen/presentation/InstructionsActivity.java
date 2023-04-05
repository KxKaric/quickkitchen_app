package comp3350.quickkitchen.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.features.ShowSteps;
import comp3350.quickkitchen.objects.ShoppingList;//sample

public class InstructionsActivity extends AppCompatActivity {
    //instruction page UI

    private ShowSteps showSteps;
    private List<String> stepsList;

    private int portionNum;
    private String chosenRecipe;
    private ArrayAdapter<String> stepsArrayAdapter;
    private ShoppingList obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        try {
            chosenRecipe = intent.getStringExtra("chosenRecipe"); // pass to shoppingList Activity
            portionNum = intent.getIntExtra("portionNum",1); // pass to shoppingList Activity

            if(chosenRecipe !=null){
                showSteps = new ShowSteps();
                stepsList=showSteps.showSteps(chosenRecipe);
            }
            final ListView listView = (ListView) findViewById(R.id.instrutions_list);
            stepsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stepsList);
            obj = new ShoppingList();
            listView.setAdapter(stepsArrayAdapter);

            TextView textView = findViewById(R.id.recipeName);
            textView.setText(chosenRecipe);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    public void buttonSLOnclick(View v){
        Intent i = new Intent(this, ShoppingListActivity.class);
        i.putExtra("chosenRecipe",chosenRecipe);
        i.putExtra("portionNum",portionNum);
        startActivity(i);
    }
}
