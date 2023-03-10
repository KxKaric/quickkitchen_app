package comp3350.quickkitchen.features;

import java.util.List;

import comp3350.quickkitchen.application.Services;
import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;


public class ShoppingList {
    private List<String> ingredients;
    private Recipe recipe;
    private RecipePersistence recipePersistence;

    public ShoppingList(){
        /**
         * Constructor that use Service to connect to db
         */
        recipePersistence = Services.getRecipePersistence();
    }

    public ShoppingList(RecipePersistence recipePersistence){
        /**
         * Overload constructor that take a persistence db as  its parameter
         */
        this.recipePersistence = recipePersistence;
    }

    public List<String> getShoppingList(String recipeName){
        /**
         * Constructor that take a persistence db as  its parameter
         */
        recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        ingredients=recipe.getIngredients();
        return ingredients;

    }
}
