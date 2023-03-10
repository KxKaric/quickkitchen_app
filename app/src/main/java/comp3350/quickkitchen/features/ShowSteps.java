package comp3350.quickkitchen.features;

import java.util.List;


import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.application.Services;

public class ShowSteps {
// use recipe name to search recipe and show the steps
    private RecipePersistence recipePersistence;

    private Recipe recipe;//recipe object

    public ShowSteps(){
        recipePersistence = Services.getRecipePersistence();
    }

    public List<String> showSteps(String recipeName){

        recipe = recipePersistence.getRecipeByName(recipeName);
        List<String> steps = recipe.getSteps();
        return steps;
    }
}
