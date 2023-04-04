package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class Filter {
    private List<Recipe> recipeList;
    private List<Recipe> filteredList;

    public Filter(List<Recipe> recipeList){
        this.recipeList = recipeList;
        filteredList = new ArrayList<>();
    }

    //filter for the recipe name
    public List<Recipe> filterName(String name){
        for(Recipe recipe : recipeList){
            if(recipe.getName().toLowerCase().contains(name.toLowerCase())){
                filteredList.add(recipe);
            }
        }
        return filteredList;
    }

    //filter for the calories
    public List<Recipe> filterCalories(String calories){
        for(Recipe recipe : recipeList){
            if(Integer.parseInt(recipe.getCalories()) <=Integer.parseInt(calories)) {
                filteredList.add(recipe);
            }
        }
        return filteredList;
    }
}
