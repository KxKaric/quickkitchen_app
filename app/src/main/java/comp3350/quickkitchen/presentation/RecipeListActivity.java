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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.features.IngredientSearch;
import comp3350.quickkitchen.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {
    private List<Recipe> recipeList;
    private List<Recipe> filteredList;
    private String chosenRecipe;
    private IngredientSearch ingSearch;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        try
        {
            ArrayList<String> result = intent.getStringArrayListExtra("result");
            ingSearch = new IngredientSearch(result);
            recipeList = ingSearch.getIngredientSearchResult();

            //display the messages at the bottom of the UI
            if(recipeList.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(),"Can not find any associated recipes, please go back and select another ingredients",Toast.LENGTH_LONG);
                toast.show();
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

                    //Log.e("test","onItemClick: "+position);
                    //Log.e("test","result: "+ingSearch.getStringList());
                    //Log.e("test","result: "+chosenRecipe);
                }
            });

            SearchView searchRecipe = findViewById(R.id.recipeSearch);
            SearchView searchCalories = findViewById(R.id.caloriesSearch);
            searchRecipe.setInputType(InputType.TYPE_CLASS_TEXT); //only allow user to type text
            searchCalories.setInputType(InputType.TYPE_CLASS_NUMBER); //only allow user to type number


            // for the function of filtering the recipe name and calories the recipe contained
            // Alert: Only one of the filters can work at one time
            filteredList = new ArrayList<>();
            searchRecipe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(!query.isEmpty()){
                        filteredList.clear();
                        for(Recipe recipe : recipeList){
                            if(recipe.getName().toLowerCase().contains(query.toLowerCase()))
                                filteredList.add(recipe);
                        }
                        updateListView(filteredList);
                        searchCalories.setQuery("",false); // force to clean the another filter's text.
                    }

                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //RecipeList.this.recipeArrayAdapter.getFilter().filter(recipe);
                    if(newText.equals(""))
                        updateListView(recipeList);
                    return false;
                }
            });
            searchCalories.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(!query.isEmpty()){
                        filteredList.clear();
                        for(Recipe recipe : recipeList){
                            if(recipe.getCalories()<=Integer.parseInt(query))
                                filteredList.add(recipe);
                        }
                        updateListView(filteredList);
                        searchRecipe.setQuery("",false); // force to clean the another filter's text.
                    }
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //RecipeList.this.recipeArrayAdapter.getFilter().filter(recipe);
                    if(newText.equals(""))
                        updateListView(recipeList);
                    return false;
                }
            });
            // filters end.

        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    //helper method for the filter function above.
    //to display the recipeList after filtering.
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
    }//end updateListView

    public void buttonNextOnclick(View v){
        Intent i = new Intent(this, InstructionsActivity.class);
        i.putExtra("chosenRecipe",chosenRecipe);
        startActivity(i);
    }

}