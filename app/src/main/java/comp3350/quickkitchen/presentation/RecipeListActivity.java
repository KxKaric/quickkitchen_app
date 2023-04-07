package comp3350.quickkitchen.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.quickkitchen.R;
import comp3350.quickkitchen.features.BookMark;
import comp3350.quickkitchen.features.Filter;
import comp3350.quickkitchen.features.IngredientSearch;
import comp3350.quickkitchen.features.Portion;
import comp3350.quickkitchen.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {
    private List<Recipe> recipeList;
    private List<Recipe> filteredList;
    private String chosenRecipe;
    private IngredientSearch ingSearch;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private Recipe recommendedRecipe; //Ranking System
    private int portionNum; //Portion feature
    public static BookMark BM = new BookMark();//Bookmark to store the recipes.

// do the search cal and UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        try
        {
            ArrayList<String> result = intent.getStringArrayListExtra("result");

            if(!result.isEmpty()) {
                ingSearch = new IngredientSearch();
                recipeList = ingSearch.searchRecipeByIngredient(result);
                if(recipeList.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Can not find any associated recipes, please go back and select another ingredients",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            //drop down menu for the portion feature
            Spinner portionSpinner = findViewById(R.id.portion_spinner);
            String[] portionOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, portionOptions);
            portionSpinner.setAdapter(adapter);

            portionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedOption = parent.getItemAtPosition(position).toString();
                    final int selectedPortion = Integer.parseInt(selectedOption);

                    portionNum = selectedPortion; // portionNum input
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // do nothing

                }
            });
            // end of the portion feature*********************


            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2,android.R.id.text1, recipeList){

                @SuppressLint("SetTextI18n")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    // Recommend only the first item
                    if (position == 0) {
                        recommendedRecipe = recipeList.get(position); //record the recommended recipe
                        TextView highlightsText = new TextView(getContext());
                        highlightsText.setText("*Recommended*");
                        highlightsText.setTextSize(20);
                        highlightsText.setTextColor(getResources().getColor(R.color.red));
                        highlightsText.setPadding(800,0,0,0);
                        ((ViewGroup)view).addView(highlightsText);
                    }

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
            Button bookMarkBtn =(Button)findViewById(R.id.bookMark);
            nextBtn.setEnabled(false);
            bookMarkBtn.setEnabled(false);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Recipe r = (Recipe) (listView.getItemAtPosition(position));
                    Portion chose = new Portion();
                    if(position!=-1)
                        selectRecipeAtPosition(position);
                    chosenRecipe = r.getName();

                    nextBtn.setEnabled(true);
                    bookMarkBtn.setEnabled(true);

                    //add to bookmark (1)
                    bookMarkBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BM.addToBookMark(r,portionNum);

                            Toast.makeText(getApplicationContext(), "Added to bookmark", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Create a new PopupMenu object
                    PopupMenu popup = new PopupMenu(RecipeListActivity.this, view);

                    // Inflate the menu layout file
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                    // Find the MenuItem for the description and set its title to the recipe's description
                    MenuItem descriptionMenuItem = popup.getMenu().findItem(R.id.menu_description);
                    descriptionMenuItem.setTitle("Ingredients List of "+r.getName()+":"+ chose.ingredientsWithPortion(r,portionNum));

                    // Show the PopupMenu
                    popup.show();
                }
            });

            // Long click : add to bookMark (2)
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    PopupMenu popupMenu = new PopupMenu(RecipeListActivity.this, view);
                    popupMenu.inflate(R.menu.bookmark_add_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.add_to_bookmark) {
                                // add to bookmark
                                Recipe selectedRecipe = (Recipe) parent.getItemAtPosition(position);
                                BM.addToBookMark(selectedRecipe,portionNum);
                                Toast.makeText(getApplicationContext(), "Added to bookmark", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });
            // End of bookmark function **********************


            // filters function **********************
            SearchView searchRecipe = findViewById(R.id.recipeSearch);
            SearchView searchCalories = findViewById(R.id.caloriesSearch);
            searchRecipe.setInputType(InputType.TYPE_CLASS_TEXT);
            searchCalories.setInputType(InputType.TYPE_CLASS_NUMBER);

            filteredList = new ArrayList<>();
            Filter myFilter = new Filter(recipeList);
            searchRecipe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(!query.isEmpty()){
                        filteredList.clear();
                        filteredList= myFilter.filterName(query);

                        updateListView(filteredList);
                        searchCalories.setQuery("",false); // force to clean the another filter's text.
                    }
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    // If user deletes the input, show the original list
                    if (newText.isEmpty()&&searchCalories.getQuery().toString().isEmpty())
                        updateListView(recipeList);
                    return false;
                }
            });
            searchCalories.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(!query.isEmpty()){
                        filteredList.clear();
                        filteredList= myFilter.filterCalories(query);

                        updateListView(filteredList);
                        searchRecipe.setQuery("",false); // force to clean the another filter's text.
                    }
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    // If user deletes the input, show the original list
                    if (newText.isEmpty()&&searchRecipe.getQuery().toString().isEmpty())
                        updateListView(recipeList);
                    return false;
                }
            });
            // End of filters function **********************

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

                // To make sure the recommended recipe doesn't change
                // when using the filter.
                if (position == 0 && recommendedRecipe==recipeList.get(position)) {
                    TextView highlightsText = new TextView(getContext());
                    highlightsText.setText("*Recommended*");
                    highlightsText.setTextSize(20);
                    highlightsText.setTextColor(getResources().getColor(R.color.red));
                    highlightsText.setPadding(800,0,0,0);
                    ((ViewGroup)view).addView(highlightsText);
                }

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
        i.putExtra("portionNum", portionNum);

        startActivity(i);
    }

}