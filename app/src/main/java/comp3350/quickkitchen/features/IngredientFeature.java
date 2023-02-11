package comp3350.quickkitchen.features;

import java.util.ArrayList;

import comp3350.quickkitchen.objects.Recipe;

public interface IngredientFeature {
    public void filterMySearch(ArrayList<String> ingredient);
    public ArrayList<Recipe> getIngredientSearchResult();
}//end interface for ingredient search
