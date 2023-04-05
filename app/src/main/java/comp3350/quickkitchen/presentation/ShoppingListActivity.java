package comp3350.quickkitchen.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.objects.ShoppingList;
import comp3350.quickkitchen.features.Portion;




public class ShoppingListActivity extends AppCompatActivity {
    //Test

    private Portion SL;
    private List<String> showList;
    private ArrayAdapter<String> sLArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);

        Intent intent = getIntent();
        String chosen;
        try {
            chosen = intent.getStringExtra("chosenRecipe");
            int portionNum = intent.getIntExtra("portionNum",1);
            if(chosen !=null){
                SL = new Portion();
                showList=SL.ingredientsWithPortion(chosen,portionNum);

                TextView textView = findViewById(R.id.SL);
                textView.setText("Shopping list for "+chosen);
                sLArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,showList);
                final ListView listView = (ListView) findViewById(R.id.shopping_list);
                listView.setAdapter(sLArrayAdapter);
            }
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }
}
