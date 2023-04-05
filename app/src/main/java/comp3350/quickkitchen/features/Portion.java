package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.application.Services;
import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class Portion {
    private List<String> ingredients;
    private Recipe recipe;
    private RecipePersistence recipePersistence;

    public Portion(){
        /**
         * Constructor that use Service to connect to db
         */
        recipePersistence = Services.getRecipePersistence();
    }

    public Portion(RecipePersistence recipePersistence){
        /**
         * Overload constructor that take a persistence db as  its parameter
         */
        this.recipePersistence = recipePersistence;
    }

    public List<String> portionImplementation(String recipeName,String portion){
        /**
         * the portion for shop list and ingredient list.
         */
        recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        ingredients=recipe.getIngredients();
        for( int i=0;i< ingredients.size();i++){
            ingredients.set(i,portion+ingredients.get(i));
        }
        return ingredients;

    }


}
