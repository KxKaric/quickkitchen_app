package com.example.myapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.feature_searchbyingredients.Recipe;
import com.example.myapplication.R;
import com.example.myapplication.feature_searchbyingredients.IngredientSearch;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {
    private List<Recipe> recipeList;

    private List<String> list;

    private String chosenRecipe;

    private IngredientSearch s;
    private ArrayAdapter recipeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent intent = getIntent();
        ArrayList<String> result = intent.getStringArrayListExtra("result");
        s = new IngredientSearch("potato"); //***************
        try
        {
            //list = new ArrayList<>();
            //recipeList = s.getIngredientSearchResult();

            ListView listView = (ListView)findViewById(R.id.recipeList);

            list = s.getStringList();


            recipeArrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
            listView.setAdapter(recipeArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    chosenRecipe = (String) (listView.getItemAtPosition(position));

                    Log.e("test","onItemClick: "+position);
                    Log.e("test","result: "+s.getStringList());
                    Log.e("test","result: "+chosenRecipe);


                }
            });

        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }
    }

    public void buttonNextOnclick(View v){
        Intent i = new Intent(this, Instructions.class);
        i.putExtra("chosenRecipe",chosenRecipe);
        startActivity(i);
    }

}