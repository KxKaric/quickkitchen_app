package comp3350.quickkitchen.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.feature_searchbyingredients.IngredientSearch;
import comp3350.quickkitchen.feature_searchbyingredients.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {
    private List<Recipe> recipeList;
    private List<Recipe> filteredList;
    private String chosenRecipe;
    private IngredientSearch s;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        try
        {
            ArrayList<String> result = intent.getStringArrayListExtra("result");

            if(!result.isEmpty()) {
                s = new IngredientSearch(result);
                recipeList = s.getIngredientSearchResult();
                //list = s.getStringList();
            }

            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1, recipeList){

                @SuppressLint("SetTextI18n")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(recipeList.get(position).getName());
                    text2.setText(recipeList.get(position).getCaloriesInString()+" calories");

                    return view;
                }
            };
            final ListView listView = (ListView)findViewById(R.id.recipeList);
            listView.setAdapter(recipeArrayAdapter);

            Button nextBtn = (Button)findViewById(R.id.nextBtn);
            nextBtn.setEnabled(false);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                    Recipe r = (Recipe) (listView.getItemAtPosition(position));
                    chosenRecipe = r.getName();

                    nextBtn.setEnabled(true);

                    Log.e("test","onItemClick: "+position);
                    Log.e("test","result: "+s.getStringList());
                    Log.e("test","result: "+chosenRecipe);

                }
            });

            SearchView searchRecipe = findViewById(R.id.recipeSearch);
            SearchView searchCalories = findViewById(R.id.caloriesSearch);
            searchRecipe.setInputType(InputType.TYPE_CLASS_TEXT);
            searchCalories.setInputType(InputType.TYPE_CLASS_NUMBER);

            filteredList = new ArrayList<>();
            searchRecipe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //RecipeList.this.recipeArrayAdapter.getFilter().filter(recipe);
                    filteredList.clear();
                    for(Recipe recipe : recipeList){
                        if(recipe.getName().toLowerCase().contains(newText.toLowerCase())){
                            filteredList.add(recipe);
                        }
                    }
                    updateListView(filteredList);
                    return true;
                }
            });
            searchCalories.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    filteredList.clear();
                    for(Recipe recipe : recipeList){
                        if(recipe.getCaloriesInString().compareTo(query)<=0){
                            filteredList.add(recipe);
                        }
                    }
                    updateListView(filteredList);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //RecipeList.this.recipeArrayAdapter.getFilter().filter(recipe);
                    return false;
                }
            });

        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }
    private void updateListView(List<Recipe> recipeList) {
        recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1, recipeList){

            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(recipeList.get(position).getName());
                text2.setText(recipeList.get(position).getCaloriesInString()+" calories");

                return view;
            }
        };
        final ListView listView = (ListView)findViewById(R.id.recipeList);
        listView.setAdapter(recipeArrayAdapter);
    }

    public void buttonNextOnclick(View v){
        Intent i = new Intent(this, Instructions.class);
        i.putExtra("chosenRecipe",chosenRecipe);
        startActivity(i);
    }

}