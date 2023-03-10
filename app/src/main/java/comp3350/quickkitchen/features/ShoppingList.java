package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;


import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.application.Services;


public class ShoppingList {
    private List<String> ingredients;
    private Recipe recipe;
    private RecipePersistence recipePersistence;

    public ShoppingList(){
        /*the constructor of Shopping list*/
        recipePersistence=Services.getRecipePersistence();

    }

    public List<String> getShoppingList(String recipeName){
        /*
        * use recipe name to find ingredient and return it as shopping list*/
        recipe = recipePersistence.getRecipeByName(recipeName);//required recipe
        ingredients=recipe.getIngredients();
        return ingredients;

    }
}
