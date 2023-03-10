package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;


import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.application.Services;

public class IngredientSearch {
// search the recipe by ingredient
    private RecipePersistence recipePersistence;
    private List<Recipe> recipeList;
    private List<String> recipeNameList = new ArrayList<String>();


    public IngredientSearch(){
/*
* the constructor of ingredient search
* */
        recipePersistence = Services.getRecipePersistence();

    }

    public List<Recipe> searchRecipeByIngredient(List<String> ingredients){

        /**
         * Search db and return a list of Recipe that use the given ingredients
         */
       this.recipeList = recipePersistence.getRecipeByIngredient(ingredients);
       return this.recipeList;
    }

    public List<String> searchRecipeNameByIngredient(List<String> ingredients){

        /**
         * Search db and return a list of Recipe name that use the given ingredients
         */

        searchRecipeByIngredient(ingredients);

        for(int i = 0; i < this.recipeList.size(); i ++){
            Recipe temp = recipeList.get(i);
            this.recipeNameList.add( temp.getName() );
        }

        return this.recipeNameList;
    }

}
