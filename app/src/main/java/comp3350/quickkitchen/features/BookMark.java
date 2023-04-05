package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;

public class BookMark {
    private List<Recipe> recipeList;

    public BookMark(){
        this.recipeList = new ArrayList<>();
    }

    public List<Recipe> addToBookMark(Recipe newRecipe){

        /**
         * Add the chosen recipe to the bookmark.
         */
        if(newRecipe!=null)
            recipeList.add(newRecipe);

        return this.recipeList;
    }

    public void delRecipeFromBookMark(Recipe r){
        /**
         * delete the chosen recipe from the bookmark.
         */
        if(!recipeList.isEmpty())
            recipeList.remove(r);
    }
    public List<Recipe> getBookMarkList(){
        return recipeList;
    }


}
