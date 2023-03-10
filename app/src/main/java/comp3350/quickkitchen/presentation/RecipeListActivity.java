package comp3350.quickkitchen.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
// do the search cal and UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        try
        {
            ArrayList<String> result = intent.getStringArrayListExtra("result");
            //Log.e("11",result.toString());

            if(!result.isEmpty()) {
                ingSearch = new IngredientSearch();
                recipeList = ingSearch.searchRecipeByIngredient(result);
                if(recipeList.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Can not find any associated recipes, please go back and select another ingredients",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1, recipeList){

                @SuppressLint("SetTextI18n")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);


                    text1.setText(recipeList.get(position).getName());
                    text2.setText(recipeList.get(position).getCalories()+" calories\t\tvegetarian: "+
                            recipeList.get(position).isVegeterian()+"\t\tglutenFree: "+
                            recipeList.get(position).isGultenfree()+"\t\tdiaryFree: "+
                            recipeList.get(position).isDairyFree() +"\t\tdifficulty: "+
                            recipeList.get(position).getDifficulty());

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
                    if(position!=-1)
                        selectRecipeAtPosition(position);
                    chosenRecipe = r.getName();

                    nextBtn.setEnabled(true);

                    //Log.e("test","onItemClick: "+position);
                    //Log.e("test","result: "+ingSearch.searchRecipeNameByIngredient(result));
                    //Log.e("test","result: "+chosenRecipe);

                    // Create a new PopupMenu object
                    PopupMenu popup = new PopupMenu(RecipeListActivity.this, view);

                    // Inflate the menu layout file
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                    // Find the MenuItem for the description and set its title to the recipe's description
                    MenuItem descriptionMenuItem = popup.getMenu().findItem(R.id.menu_description);
                    descriptionMenuItem.setTitle("Ingredients List of "+r.getName()+":"+ r.getIngredients());

                    // Show the PopupMenu
                    popup.show();
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
                    if(!query.isEmpty()){
                        filteredList.clear();
                        for(Recipe recipe : recipeList){
                            if(recipe.getName().toLowerCase().contains(query.toLowerCase())){
                                filteredList.add(recipe);
                            }
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
                            if(Integer.parseInt(recipe.getCalories()) <=Integer.parseInt(query)) {
                                filteredList.add(recipe);
                            }
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
                text2.setText(recipeList.get(position).getCalories()+" calories");

                return view;
            }
        };
        final ListView listView = (ListView)findViewById(R.id.recipeList);
        listView.setAdapter(recipeArrayAdapter);
    }

    //show selected recipe's name and calories in the search bar.
    public void selectRecipeAtPosition(int position) {
        Recipe selected = recipeArrayAdapter.getItem(position);

        SearchView editCalories = (SearchView) findViewById(R.id.caloriesSearch);
        SearchView editRecipeName = (SearchView)findViewById(R.id.recipeSearch);

        editCalories.setQuery(selected.getCalories(),false);
        editRecipeName.setQuery(selected.getName(),false);
    }
    public void buttonNextOnclick(View v){
        Intent i = new Intent(this, InstructionsActivity.class);
        i.putExtra("chosenRecipe",chosenRecipe);
        startActivity(i);
    }

}