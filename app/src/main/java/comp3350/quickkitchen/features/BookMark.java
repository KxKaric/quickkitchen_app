package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.objects.Recipe;
import comp3350.quickkitchen.persistence.RecipePersistence;

public class BookMark {
    private List<Recipe> recipeList;
    private Portion portionClass;

    public BookMark(){
        this.recipeList = new ArrayList<>();
        this.portionClass = new Portion();
    }

    public BookMark(RecipePersistence recipePersistance){
        /**
         * Constructor with dependency injection
         */
        this.recipeList = new ArrayList<>();
        this.portionClass = new Portion(recipePersistance);
    }

    public List<Recipe> addToBookMark(Recipe newRecipe, int portion){

        /**
         * Add the chosen recipe to the bookmark with no duplicates.
         */
        if(newRecipe != null && !recipeList.contains(newRecipe)) {
            //updates the ingredients list with user's input portionNum
            newRecipe.setIngredients(portionClass.ingredientsWithPortion(newRecipe, portion));
            recipeList.add(newRecipe);
        }
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
